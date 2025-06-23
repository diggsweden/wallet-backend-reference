// SPDX-FileCopyrightText: 2025 diggsweden/wallet-backend-reference
//
// SPDX-License-Identifier: EUPL-1.2

package com.example.demo.application.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.model.CreateUserDto;
import com.example.demo.application.model.UserDto;

@RequestMapping("/users")
@RestController
public class UserController {

  @GetMapping("{id}")
  public UserDto getUser(String id) {
    return null;
  }

  @PostMapping
  public ResponseEntity<UserDto> addUser(CreateUserDto user) {
    return ResponseEntity.created(URI.create("/users/" + "id")).build(); // ?
  }
}
