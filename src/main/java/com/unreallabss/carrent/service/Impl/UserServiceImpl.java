package com.unreallabss.carrent.service.Impl;


import com.querydsl.core.BooleanBuilder;
import com.unreallabss.carrent.config.ConfigProperties;
import com.unreallabss.carrent.domain.user.PasswordResetToken;
import com.unreallabss.carrent.domain.user.QUser;
import com.unreallabss.carrent.domain.user.User;
import com.unreallabss.carrent.domain.base.ComplexValidationException;
import com.unreallabss.carrent.domain.criteria.UserCriteria;
import com.unreallabss.carrent.enums.Status;
import com.unreallabss.carrent.enums.UserStatus;
import com.unreallabss.carrent.enums.UserType;
import com.unreallabss.carrent.repository.PasswordTokenRepository;
import com.unreallabss.carrent.repository.UserRepository;
import com.unreallabss.carrent.service.CryptoService;
import com.unreallabss.carrent.service.UserService;
import com.unreallabss.carrent.util.PasswordGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordTokenRepository passwordTokenRepository;

    @Autowired
    private ConfigProperties configProps;

    @Autowired
    private CryptoService cryptoService;

    @Transactional
    @Override
    public User save(User user) {
        BooleanBuilder builder =
                new BooleanBuilder(QUser.user.username.equalsIgnoreCase(user.getUsername()));
        builder.and(QUser.user.status.ne(UserStatus.DELETED));
        Optional<User> persistedUser = userRepository.findOne(builder);
        if (persistedUser.isPresent()) {
            log.info("platformUserCreateRequest.username.duplicate {}", persistedUser.get().getUsername());
            throw new ComplexValidationException("User registration", "this Email already registered");
        }
        String password = user.getPassword(); // since string is immutable create new object

        if (StringUtils.isNoneBlank(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(passwordEncoder.encode(PasswordGenerator.generatePassword()));
        }
        user.setStatus(UserStatus.ACTIVE);
        user.setFailedLoginAttemptCount(0);
        user.setDateJoin(LocalDate.now());
        //if (user.getRole() == null) user.setRole(UserType.USER);
        //user.setPassWord(passwordEncoder.encode(user.getPassWord()));
        return userRepository.save(user);

        ///TODO need to send Email to user with password
    }

    @Transactional(readOnly = true)
    @Override
    public Page<User> search(UserCriteria criteria) {

        PageRequest page = PageRequest.of(criteria.getPageNumber() - 1, criteria.getPageSize(),
                Sort.by(Sort.Direction.fromOptionalString(criteria.getSortDirection()).orElse(Sort.Direction.DESC),
                        criteria.getSortProperty()));

        Page<User> users = null;

        //BooleanBuilder booleanBuilder = new BooleanBuilder(QUser.user.status.ne(Status.DELETED));

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        /*if (StringUtils.isNotBlank(criteria.getEmail())) {
            booleanBuilder.and(QUser.user.email.containsIgnoreCase(criteria.getEmail()));
        }
        if (criteria.getRole() != null ) {
            booleanBuilder.and(QUser.user.role.eq(UserType.valueOf(String.valueOf(criteria.getRole()))));
        }*/

        if (booleanBuilder.hasValue()){
            users = userRepository.findAll(booleanBuilder, page);
        } else {
            users = userRepository.findAll(page);
        }
        return users;
    }

    @Transactional(readOnly = true)
    @Override
    public User retrieve(Long id) {
        Optional<User> userPersisted = userRepository.findById(id);
        if (!userPersisted.isPresent()) {
            throw new ComplexValidationException("user retrieval","User (%s) not found  ");
        }
        return userPersisted.get();
    }


    @Transactional
    @Override
    public User update(User user) {
        Optional<User> userPersisted = userRepository.findById(user.getId());
        if (!userPersisted.isPresent()) {
            throw new ComplexValidationException("User retrieval", "Cannot find any User ");
        } else {
            BooleanBuilder builder =
                    new BooleanBuilder(QUser.user.username.equalsIgnoreCase(user.getUsername()))
                            .and(QUser.user.id.ne(user.getId()));
            boolean usernameExists = userRepository.exists(builder);
            if (usernameExists) {
                throw new ComplexValidationException("username", "User name already exist");
            }
            User userDb = userPersisted.get();
            //updateFields(user, userDb);
            /*if (user.getVehicle() != null) {
                updateVehicles(user, userDb);
            }*/

            //TODO update fields
            return userRepository.save(userDb);
        }
    }

    /*private void updateFields(User user, User userDb) {
        if (user.getFirstName() != null) userDb.setFirstName(user.getFirstName());
        if (user.getLastName() != null) userDb.setLastName(user.getLastName());
        if (user.getContactNo() != null) userDb.setContactNo(user.getContactNo());
        if (user.getDateJoin() != null) userDb.setDateJoin(user.getDateJoin());
        if (user.getAge() != null) userDb.setAge(user.getAge());
        if (user.getGenderType() != null) userDb.setGenderType(user.getGenderType());
        if (user.getNic() != null) userDb.setNic(user.getNic());
        if (user.getNationality() != null) userDb.setNationality(user.getNationality());
        if (user.getImage() != null) userDb.setImage(user.getImage());
        if (user.getReligion() != null) userDb.setReligion(user.getReligion());
        if (user.getPassWord() != null) userDb.setPassWord(passwordEncoder.encode(user.getPassWord()));
        //if (user.getPassWord() != null) userDb.setPassWord((user.getPassWord()));
        if (user.getRole() != null) userDb.setRole(user.getRole());

    }

    private void updateVehicles(User user, User userDb) {
        List<Vehicle> newVehicles = new ArrayList<>();
        List<Vehicle> deletedVehicles = new ArrayList<>();

        if (user.getVehicle() != null && !user.getVehicle().isEmpty()) {
            if (user.getVehicle().size() >= userDb.getVehicle().size()) {
                for (Vehicle updatedVehicle : user.getVehicle()) {
                    if (updatedVehicle.getId() == null) {
                        updatedVehicle.setUser(userDb);
                        newVehicles.add(updatedVehicle);
                    } else {
                        Optional<Vehicle> vehicleFound = userDb.getVehicle().stream()
                                .filter(vehicle -> vehicle.getId().equals(updatedVehicle.getId())).findFirst();
                        if (vehicleFound.isPresent()) {
                            updateExistingVehicle(vehicleFound.get(), updatedVehicle);
                        } else {
                            throw new ComplexValidationException("Vehicle update", "Invalid vehicle id. " + updatedVehicle.getId());
                        }
                    }
                }
            } else {
                for (Vehicle existingVehicle : userDb.getVehicle()) {
                    Optional<Vehicle> vehicleFound = user.getVehicle().stream()
                            .filter(vehicle -> vehicle.getId().equals(existingVehicle.getId())).findFirst();
                    if (vehicleFound.isPresent()) {
                        updateExistingVehicle(existingVehicle, vehicleFound.get());
                    } else {
                        deletedVehicles.add(existingVehicle);
                    }
                }
            }
            userDb.getVehicle().addAll(newVehicles);
            userDb.getVehicle().removeAll(deletedVehicles);
        }
    }

    private void updateExistingVehicle(Vehicle existingVehicle, Vehicle updatedVehicle) {
        if (updatedVehicle.getVehicleNo() != null) existingVehicle.setVehicleNo(updatedVehicle.getVehicleNo());
        if (updatedVehicle.getModel() != null) existingVehicle.setModel(updatedVehicle.getModel());
        if (updatedVehicle.getYear() != null) existingVehicle.setYear(updatedVehicle.getYear());
        if (updatedVehicle.getColor() != null) existingVehicle.setColor(updatedVehicle.getColor());
        if (updatedVehicle.getType() != null) existingVehicle.setType(updatedVehicle.getType());
        if (updatedVehicle.getDescription() != null) existingVehicle.setDescription(updatedVehicle.getDescription());
        if (updatedVehicle.getStatus() != null) existingVehicle.setStatus(updatedVehicle.getStatus());
        if (updatedVehicle.getBrand() != null) existingVehicle.setBrand(updatedVehicle.getBrand());
        updatedVehicle.setStatus(Status.ACTIVE);
    }*/


    @Transactional
    @Override
    public User delete(Long id) {
        User user = userRepository.getReferenceById(id);
        if (user != null) {
            user.setStatus(UserStatus.DELETED);
            return userRepository.save(user);
        }else {
            throw new ComplexValidationException("User retrieval", "Cannot find any User ");
        }
    }

    @Override
    public User logIn(User user) {
        User userPersisted = userRepository.findByUsername(user.getUsername());
        if (userPersisted != null) {
            /*if (passwordEncoder.matches(user.getPassWord(), userPersisted.getPassWord())) {
                userPersisted.setUserLogging(LocalDateTime.now());
                userRepository.save(userPersisted);
                return userPersisted;
            }*/
        }
        throw new ComplexValidationException(user.getUsername(), "User credentials Invalid");
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        log.info("loading user email {}", email);
        User user = userRepository.findByUsername(email);
        log.info("loaded user {}", user);

        if (user != null) {
            user.getRoles().size();
            // user.getRole().getAuthorities().size();
            user.getRoles().forEach(r -> r.getAuthorities().size());
            return user;
        } else {
            throw new UsernameNotFoundException("User not found!");
        }
    }

    @Override
    public void updateForSuccessLogin(User user) {
        if (user.getStatus() == UserStatus.TEMP_LOCKED_BAD_CREDENTIALS) {
            Optional<User> exUser = userRepository.findById(user.getId());
            if (exUser.isPresent()) {
                exUser.get().setFailedLoginAttemptCount(0);
                exUser.get().setLastLoginDate(LocalDateTime.now());
                exUser.get().setStatus(UserStatus.ACTIVE);
                userRepository.save(exUser.get());
            }
        } else {
            userRepository.updateForSuccessLogin(user.getId(), LocalDateTime.now());
        }
    }

    @Override
    public void updateForBadCredentialLoginFailure(String email) {
        User user = userRepository.findByUsername(email);

        if (user != null) {
            if (user.getFailedLoginAttemptCount() + 1
                    >= configProps.getAuth().getMaxFailedLoginAttemptsForAccountLock()) {
                user.setStatus(UserStatus.TEMP_LOCKED_BAD_CREDENTIALS);
            }
            user.setFailedLoginAttemptCount(user.getFailedLoginAttemptCount() + 1);
            user.setLastFailedLoginDate(LocalDateTime.now());
            userRepository.save(user);
        }
    }

    @Transactional
    @Override
    public void resetPassword(String code, String password) {
        String email = cryptoService.decryptString(code);
        User user = userRepository.findByUsername(email);
        if (user == null) {
            throw new ComplexValidationException("user", "Invalid use id");
        }
        password = passwordEncoder.encode(password);
        userRepository.updatePasswordChange(user.getId(), password);
    }

    @Transactional
    @Override
    public User updateUserProfile(User user) {
        Optional<User> currentUser = userRepository.findById(user.getId());

        if (!currentUser.isPresent()) {
            throw new ComplexValidationException("user", "Invalid use id");
        }

        currentUser.get().setUsername(user.getUsername());
        userRepository.save(currentUser.get());

        return currentUser.get();
    }

    @Transactional
    @Override
    public void delete(List<User> users) {
        users.forEach(
                user -> {
                    Optional<User> optionalUser = userRepository.findById(user.getId());
                    if (optionalUser.isPresent()) {
                        user = optionalUser.get();
                        user.setStatus(UserStatus.DELETED);
                        try {
                            userRepository.save(user);
                        } catch (Exception ex) {
                            throw new ComplexValidationException(
                                    "user", "Error occured" + user.getId());
                        }
                    } else {
                        throw new ComplexValidationException("user", "Invalid use id");
                    }
                });
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> retrieveUsersByIds(String ids) {
        List<Long> userIds =
                Arrays.asList(ids.split(",")).stream().map(Long::valueOf).collect(Collectors.toList());

        log.info("userIds 2--->" + userIds);

        return userRepository.retrieveUsersByIds(userIds);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        myToken.setExpiryDate(LocalDateTime.now().plusDays(1));
        passwordTokenRepository.save(myToken);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByPasswordResetToken(String token) {
        PasswordResetToken passwordResetToken = passwordTokenRepository.findByToken(token);
        if (passwordResetToken != null) {
            return passwordResetToken.getUser();
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public void changeUserPassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public boolean checkIfValidOldPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    @Override
    public String getUserFirstName(Long createdUserId) {
        return retrieve(createdUserId).getFirstName();
    }

}
