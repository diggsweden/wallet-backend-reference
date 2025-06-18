// SPDX-FileCopyrightText: 2025 diggsweden/wallet-backend-reference
//
// SPDX-License-Identifier: EUPL-1.2

package com.example.demo.domain.service;

import com.example.demo.domain.model.User;
import com.example.demo.infrastructure.model.UserEntity;

// TODO: Vote if we wanna use MapStruct/another lib or do it manually
public class UserMapper {

  public static User toDomain(UserEntity user) {
    return new User(user.getId(), user.getAddress(), user.getName(), user.getBirthDate());
  }

}
