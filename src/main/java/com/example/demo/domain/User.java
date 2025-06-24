// SPDX-FileCopyrightText: 2025 diggsweden/wallet-backend-reference
//
// SPDX-License-Identifier: EUPL-1.2

package com.example.demo.domain;

import java.time.LocalDate;
import java.util.UUID;


// TODO: We have decided to not use Lombok annotation. But we haven't looked into other alternatives
// like RecordBuilder
// TODO: Vote how many fields are OK until builder pattern is needed

// Immutable classes (records) whenever possible
// Builder pattern should be used for bigger classes
public record User(
    UUID id,
    String address,
    String name,
    LocalDate birthDate) {

  public User {
    // TODO validate?
  }
}
