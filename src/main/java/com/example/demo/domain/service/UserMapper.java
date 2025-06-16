package com.example.demo.domain.service;

import com.example.demo.domain.model.User;
import com.example.demo.infrastructure.model.UserEntity;

public class UserMapper {

    public static User toDomain(UserEntity user) {
        return new User(user.getId(), user.getAddress(), user.getName(), user.getBirthDate());
    }

}
