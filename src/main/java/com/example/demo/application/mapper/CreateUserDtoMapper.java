// SPDX-FileCopyrightText: 2025 diggsweden/wallet-backend-reference
//
// SPDX-License-Identifier: EUPL-1.2

package com.example.demo.application.mapper;

import com.example.demo.application.model.CreateUserDto;
import com.example.demo.domain.model.User;

import java.util.UUID;

public final class CreateUserDtoMapper {

    private CreateUserDtoMapper() {}

    public static User toDomain(CreateUserDto dto) {
        if (dto == null) {
            return null;
        }
        return new User(
                UUID.randomUUID(),
                dto.address(),
                dto.name(),
                dto.birthDate()
        );
    }

    public static CreateUserDto toDto(User user) {
        if (user == null) {
            return null;
        }
        return new CreateUserDto(
                user.address(),
                user.name(),
                user.birthDate()
        );
    }
}