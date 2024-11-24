package com.unreallabss.carrent.service.Impl;

import com.querydsl.core.BooleanBuilder;
import com.unreallabss.carrent.domain.base.ComplexValidationException;
import com.unreallabss.carrent.domain.criteria.RoleCriteria;
import com.unreallabss.carrent.domain.user.QRole;
import com.unreallabss.carrent.domain.user.Role;
import com.unreallabss.carrent.enums.Status;
import com.unreallabss.carrent.repository.RoleRepository;
import com.unreallabss.carrent.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	/*@Autowired
    ApplicationEventPublisher applicationEventPublisher;*/

	/*@Autowired
	ModelMapper modelMapper;*/

	@Transactional
	@Override
	public Role create(Role role) {
		role.setPredefine(Boolean.FALSE);
		return roleRepository.save(role);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		try {
			Optional<Role> persistedRole = roleRepository.findById(id);
			if (persistedRole.isPresent()) {
				Role dbRole = persistedRole.get();
				dbRole.setStatus(Status.DELETED);
				roleRepository.save(dbRole);
			}
		} catch (EmptyResultDataAccessException e) {
			throw new ComplexValidationException("role", "Role not exist");
		}
	}

	/*@Transactional(readOnly = true)
	@Override
	public RoleViewResponse retrieve(Long id) {
		Optional<Role> role = roleRepository.findById(id);

		if (!role.isPresent()) {
			throw new ComplexValidationException("role", "platformRoleViewRequest.roleNotExist");
		}

		role.get().getAuthorities().size();
		role.get().getUsers().size();

		Set<Authority> authorities =role.get().getAuthorities().stream().filter(Authority::getVisibleInRoleCreation).collect(Collectors.toSet());
		role.get().setAuthorities(authorities);

		RoleViewResponse roleResponse = modelMapper.map(role.get(), RoleViewResponse.class);
		applicationEventPublisher.publishEvent(new ViewEvent(roleResponse, Role.class));

		return roleResponse;
	}*/

	@Transactional(readOnly = true)
	@Override
	public Page<Role> search(RoleCriteria criteria) {
		PageRequest page = PageRequest.of(criteria.getPageNumber() - 1, criteria.getPageSize(),
				Sort.by(Sort.Direction.fromOptionalString(criteria.getSortDirection()).orElse(Sort.Direction.DESC), criteria.getSortProperty()));
		Page<Role> roles = null;
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		if (StringUtils.isNotBlank(criteria.getKeyword())) {
			booleanBuilder.or(QRole.role.name.containsIgnoreCase(criteria.getKeyword()));
		}

		if (booleanBuilder.hasValue()) {
			roles = roleRepository.findAll(booleanBuilder, page);
		} else {
			roles = roleRepository.findAll(page);
		}

		return roles;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Role> retrieveForSuggestions(String keyword) {
        BooleanBuilder builder = new BooleanBuilder(QRole.role.name.ne("SUPER_ADMIN"));
		if (StringUtils.isNotBlank(keyword)) {
			builder.and(QRole.role.name.containsIgnoreCase(keyword));
		}
        return (List<Role>) roleRepository.findAll(builder);
	}

	@Transactional
	@Override
	public void delete(List<Role> roles) {
		roles.forEach(role -> {
			/*QRole.role.id.eq(role.getId())).and(QRole.role.users.isNotEmpty()*/
			BooleanBuilder builder = new BooleanBuilder();
			/*boolean usersExist = roleRepository.exists(builder);
			if (usersExist) {
				throw new ComplexValidationException("role", "platformRoleDeleteRequest.roleAttachedToUsers.roleId."
						+ cryptoService.encryptEntityId(role.getId()));
			}*/

			try {
				roleRepository.deleteById(role.getId());
			} catch (EmptyResultDataAccessException e) {
				throw new ComplexValidationException("role", "Role not exist"
						+ role.getId());
			}
		});
	}

	@Transactional(readOnly = true)
	@Override
	public Role retrieve(Long roleId) {

		Optional<Role> role = roleRepository.findById(roleId);

		if (!role.isPresent()) {
			throw new ComplexValidationException("role", "Role not exist");
		}
		return role.get();
	}

	public Boolean checkUserRole(Set<Role> roles, String role) {
		Boolean roleFound = Boolean.FALSE;
		if (roles != null && roles.size() > 0) {
			for (Role role1 : roles) {
				if (role1.getName().equalsIgnoreCase(role)) {
					roleFound = Boolean.TRUE;
				}
			}

		}
		return roleFound;
	}

	@Transactional
	@Override
	public Role update(Role role) {

		Optional<Role> roleDb = roleRepository.findById(role.getId());

		if (!roleDb.isPresent()) {
			throw new ComplexValidationException("role", "Role not exist");
		}

		BooleanBuilder builder = new BooleanBuilder(QRole.role.name.equalsIgnoreCase(role.getName()))
				.and(QRole.role.id.ne(role.getId()));
		boolean nameExists = roleRepository.exists(builder);
		if (nameExists) {
			throw new ComplexValidationException("name", "Role name already exist");
		}

		if(role.getName() != null) {
			roleDb.get().setName(role.getName());
		}

		return roleRepository.save(roleDb.get());
	}

	@Transactional(readOnly = true)
	@Override
	public Role findByName(String shop) {
		return roleRepository.findByName(shop);
	}
}
