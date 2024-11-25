package com.unreallabss.carrent.service;


import com.unreallabss.carrent.domain.user.User;
import com.unreallabss.carrent.domain.criteria.UserCriteria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    User save(User user);

    Page<User> search(UserCriteria criteria);

    User retrieve(Long id);

    User update(User user);

    User delete(Long id);

    User logIn(User user);

    void updateForSuccessLogin(User user);

    void updateForBadCredentialLoginFailure(String email);

    void resetPassword(String code, String password);

    User updateUserProfile(User user);

    void delete(List<User> users);

    List<User> retrieveUsersByIds(String userIds);

    User findByUsername(String username);

    void createPasswordResetTokenForUser(User user, String token);

    User getUserByPasswordResetToken(String token);

    void changeUserPassword(User user, String newPassword);

    boolean checkIfValidOldPassword(User user, String oldPassword);

    String getUserFirstName(Long createdUserId);
}
