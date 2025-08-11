// SPDX-FileCopyrightText: 2025 diggsweden/wallet-backend-reference
//
// SPDX-License-Identifier: EUPL-1.2

package com.example.demo.application.model;

import java.time.LocalDate;
import java.util.UUID;

// TODO validate?
public record UserDto(
    UUID id,
    String address,
    String name,
    LocalDate birthDate) {

}
