// SPDX-FileCopyrightText: 2025 diggsweden/wallet-backend-reference
//
// SPDX-License-Identifier: EUPL-1.2

package com.example.demo.application.mapper;

import com.example.demo.application.model.UserDto;
import com.example.demo.domain.model.User;

public final class UserDtoMapper {

  private UserDtoMapper() {}

  public static UserDto toDto(User user) {
    return new UserDto(user.address(), user.name(), user.birthDate());
  }
}
