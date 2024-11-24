package com.unreallabss.carrent.service;

import com.unreallabss.carrent.domain.criteria.RoleCriteria;
import com.unreallabss.carrent.domain.user.Role;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface RoleService {

	Role create(Role role);

	void delete(Long id);

	Page<Role> search(RoleCriteria criteria);

	List<Role> retrieveForSuggestions(String keyword);

    void delete(List<Role> roles);


	Role retrieve(Long roleId);

	Boolean checkUserRole(Set<Role> roles, String roleString);

    Role update(Role role);

	Role findByName(String shop);

}
