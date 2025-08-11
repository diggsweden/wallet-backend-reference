// SPDX-FileCopyrightText: 2025 diggsweden/wallet-backend-reference
//
// SPDX-License-Identifier: EUPL-1.2

package com.example.demo.infrastructure.mapper;

import com.example.demo.domain.model.User;
import com.example.demo.infrastructure.model.UserEntity;

public final class UserEntityMapper {

    private UserEntityMapper() {}

    public static UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }
        UserEntity entity = new UserEntity();
        entity.setId(user.id());
        entity.setName(user.name());
        entity.setAddress(user.address());
        entity.setBirthDate(user.birthDate());
        return entity;
    }

    public static User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return new User(
                entity.getId(),
                entity.getAddress(),
                entity.getName(),
                entity.getBirthDate()
        );
    }
}