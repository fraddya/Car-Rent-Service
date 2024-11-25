package com.unreallabss.carrent.repository;


import com.unreallabss.carrent.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {
    User findByUsername(String username);

    @Modifying
    @Query("update User u set u.lastLoginDate=:loginDate, u.failedLoginAttemptCount=0 where u.id=:userId")
    void updateForSuccessLogin(@Param("userId") Long userId, @Param("loginDate") LocalDateTime loginDate);

    @Modifying
    @Query("update User u set u.password = :password where u.id = :userId")
    void updatePasswordChange(@Param("userId") Long userId, @Param("password") String password);

    @Query("select u from User u where u.id in (:userIds)")
    List<User> retrieveUsersByIds(@Param("userIds") List<Long> userIds);
}
