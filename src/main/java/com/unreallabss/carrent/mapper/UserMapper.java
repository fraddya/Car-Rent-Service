package com.unreallabss.carrent.mapper;


import com.unreallabss.carrent.domain.User;
import com.unreallabss.carrent.domain.criteria.UserCriteria;
import com.unreallabss.carrent.dto.request.user.UserCreateRequest;
import com.unreallabss.carrent.dto.request.user.UserSearchRequest;
import com.unreallabss.carrent.dto.request.user.UserUpdateRequest;
import com.unreallabss.carrent.dto.response.user.UserCreateResponse;
import com.unreallabss.carrent.dto.response.user.UserSearchResponse;
import com.unreallabss.carrent.enums.Status;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",imports = {Status.class})
public interface UserMapper {

    User mapToUser(UserCreateRequest request);

    User mapToUserUpdate(UserUpdateRequest request);

    UserCriteria mapToCriteria(UserSearchRequest request);

    List<UserSearchResponse> mapToSearchResponse(List<User> content);

    UserSearchResponse mapToUserViewResponse(User user);

    UserCreateResponse mapToUpdateResponse(User userUpdate);

}
