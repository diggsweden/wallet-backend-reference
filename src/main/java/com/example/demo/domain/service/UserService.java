// SPDX-FileCopyrightText: 2025 diggsweden/wallet-backend-reference
//
// SPDX-License-Identifier: EUPL-1.2

package com.example.demo.domain.service;

import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.application.exception.NotFoundException;
import com.example.demo.infrastructure.repository.UserRepository;


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
        .orElseThrow(() -> new NotFoundException());

    // TODO: configure logging
    LOGGER.info("test");
  }
}
