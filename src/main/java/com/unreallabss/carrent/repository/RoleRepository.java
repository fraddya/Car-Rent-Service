package com.unreallabss.carrent.repository;

import com.unreallabss.carrent.domain.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

//import org.springframework.data.repository.history.RevisionRepository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, QuerydslPredicateExecutor<Role> {

    Role findByName(String shop);
}
