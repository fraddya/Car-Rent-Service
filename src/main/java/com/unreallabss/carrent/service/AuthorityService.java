package com.unreallabss.carrent.service;


import com.unreallabss.carrent.domain.user.Authority;
import com.unreallabss.carrent.enums.AuthorityType;

import java.util.List;

public interface AuthorityService {

	List<Authority> retrieve(AuthorityType authorityType);

}
