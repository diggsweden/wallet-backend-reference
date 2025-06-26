// SPDX-FileCopyrightText: 2025 diggsweden/wallet-backend-reference
//
// SPDX-License-Identifier: EUPL-1.2

package com.example.demo.domain.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import io.soabase.recordbuilder.core.RecordBuilder;
/**
 * Immutable record for User data. 
 * Recommend use of builder pattern, i.e. 
 * UserBuilder.builder()
 */
@RecordBuilder
public record User(
    UUID id,
    String address,
    String name,
    LocalDate birthDate) {

  public User {
    Objects.requireNonNull(name, "Name cannot be null");
  }
}
