package com.unreallabss.carrent.service.Impl;

import com.unreallabss.carrent.domain.user.Authority;
import com.unreallabss.carrent.enums.AuthorityType;
import com.unreallabss.carrent.repository.AuthorityRepository;
import com.unreallabss.carrent.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Authority> retrieve(AuthorityType authorityType) {
		return authorityRepository.findByTypeAndVisibleInRoleCreation(authorityType, Boolean.TRUE);
	}

}
