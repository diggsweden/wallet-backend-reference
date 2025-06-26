// SPDX-FileCopyrightText: 2025 diggsweden/wallet-backend-reference
//
// SPDX-License-Identifier: EUPL-1.2
package com.example.demo.application.domain.model;

import static org.junit.Assert.assertThrows;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.example.demo.domain.model.User;
import com.example.demo.domain.model.UserBuilder;

class UserTest {

  @Test
  void rejectBuilderWithNullName() {
    UserBuilder builder = UserBuilder.builder()
        .address("Street 1")
        .birthDate(LocalDate.now());
    assertThrows(NullPointerException.class, builder::build);
  }

  @Test
  void rejectConstructorWithNullName() {
    assertThrows(NullPointerException.class,
        () -> new User(UUID.randomUUID(), "Hello", null, LocalDate.now()));
  }
}
