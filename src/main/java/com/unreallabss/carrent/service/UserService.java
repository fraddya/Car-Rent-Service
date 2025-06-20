package com.unreallabss.carrent.service;


import com.unreallabss.carrent.domain.User;
import com.unreallabss.carrent.domain.criteria.UserCriteria;
import org.springframework.data.domain.Page;

public interface UserService {

    User save(User user);

    Page<User> search(UserCriteria criteria);

    User retrieve(Long id);

    User update(User user);

    User delete(Long id);

    User logIn(User user);
}
