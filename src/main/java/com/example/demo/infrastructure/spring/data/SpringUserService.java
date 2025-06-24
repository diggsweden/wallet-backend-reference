// SPDX-FileCopyrightText: 2025 diggsweden/wallet-backend-reference
//
// SPDX-License-Identifier: EUPL-1.2

package com.example.demo.infrastructure.spring.data;

import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.application.exception.NotFoundException;
import com.example.demo.domain.model.User;


// TODO: Vote in which layer we do the mapping
public class SpringUserService {

  private static final Logger LOGGER = LoggerFactory.getLogger(SpringUserService.class);

  private UserRepository repository;

  public SpringUserService(UserRepository repository) {
    this.repository = Objects.requireNonNull(repository);
  }

  void todo(UUID id) {
    var unusedUser = repository.findById(id)
        .map(UserMapper::toDomain)
        .orElseThrow(() -> new NotFoundException());

    // TODO: configure logging
    LOGGER.info("test");
  }

  public User get(UUID id) {
    return repository.findById(id)
        .map(UserMapper::toDomain)
        .orElseThrow(() -> new NotFoundException());
  }
}
