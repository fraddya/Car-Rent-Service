package com.unreallabss.carrent.domain.criteria;

import com.unreallabss.carrent.domain.user.User;
import lombok.Data;

@Data
public class UserCriteria extends User {

    private String username;

    private String passWord;

    private String keyword;

    private Integer pageNumber;

    private Integer pageSize;

    private String sortProperty;

    private String sortDirection;

}
