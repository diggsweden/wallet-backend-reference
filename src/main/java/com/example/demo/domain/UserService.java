// SPDX-FileCopyrightText: 2025 diggsweden/wallet-backend-reference
//
// SPDX-License-Identifier: EUPL-1.2

package com.example.demo.domain;

import java.util.UUID;

public interface UserService {

  User get(UUID id);
}
