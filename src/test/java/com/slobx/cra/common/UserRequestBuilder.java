package com.slobx.cra.common;

import com.slobx.cra.application.dto.UserDTO;
import com.slobx.cra.infrastructure.persistence.entity.User;

public class UserRequestBuilder {

    public static UserDTO buildUserDTO() {
        return UserDTO.builder().id(1L).userName("test_user_name").phoneNumber("test_phone_number").build();
    }

    public static User buildUserEntity() {
        return User.builder().id(1L).userName("test_user_name").phoneNumber("test_phone_number").build();
    }
}
