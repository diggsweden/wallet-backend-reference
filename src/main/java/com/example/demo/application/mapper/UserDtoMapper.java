// SPDX-FileCopyrightText: 2025 diggsweden/wallet-backend-reference
//
// SPDX-License-Identifier: EUPL-1.2

package com.example.demo.application.mapper;

import com.example.demo.application.model.UserDto;
import com.example.demo.domain.model.User;

public final class UserDtoMapper {

    private UserDtoMapper() {}

    public static User toDomain(UserDto dto) {
        if (dto == null) {
            return null;
        }
        return new User(
                dto.id(),
                dto.address(),
                dto.name(),
                dto.birthDate()
        );
    }

    public static UserDto toDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserDto(
                user.id(),
                user.address(),
                user.name(),
                user.birthDate()
        );
    }
}