// SPDX-FileCopyrightText: 2025 diggsweden/wallet-backend-reference
//
// SPDX-License-Identifier: EUPL-1.2

package com.example.demo.domain.service;

import com.example.demo.application.exception.UserNotFoundException;
import com.example.demo.infrastructure.repository.UserRepository;
import java.util.Objects;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Vote in which layer we do the mapping
public class UserService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

  private UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = Objects.requireNonNull(repository);
  }

  void todo(UUID id) {
    var unusedUser = repository.findById(id)
        .map(UserMapper::toDomain)
        .orElseThrow(() -> new UserNotFoundException());

    // TODO: configure logging
    LOGGER.info("test");
  }
}
