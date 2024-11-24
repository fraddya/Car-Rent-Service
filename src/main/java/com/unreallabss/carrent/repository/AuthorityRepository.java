package com.unreallabss.carrent.repository;

import com.unreallabss.carrent.domain.user.Authority;
import com.unreallabss.carrent.enums.AuthorityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long>, QuerydslPredicateExecutor<Authority> {

	List<Authority> findByTypeAndVisibleInRoleCreation(AuthorityType authorityType, Boolean visibleInRoleCreation);

}
