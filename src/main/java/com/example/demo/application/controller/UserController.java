// SPDX-FileCopyrightText: 2025 diggsweden/wallet-backend-reference
//
// SPDX-License-Identifier: EUPL-1.2

package com.example.demo.application.controller;

import java.net.URI;
import java.time.LocalDate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.application.exception.InputValidationException;
import com.example.demo.application.exception.InputValidationException.Error;
import com.example.demo.application.model.CreateUserDto;
import com.example.demo.application.model.UserDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/users")
public class UserController {

  @GetMapping("{id}")
  public UserDto getUser(@PathVariable String id) {
    return null;
  }

  // TODO well defined openapi docs
  // IF TOO CLUTTERY MOVE TO INTERFACE?
  @Operation(responses = {
      @ApiResponse(responseCode = "201"),
      @ApiResponse(responseCode = "4xx"),
      @ApiResponse(responseCode = "5xx"),
  })
  @PostMapping
  public ResponseEntity<UserDto> addUser(CreateUserDto user) {
    return ResponseEntity.created(URI.create("/users/" + "id")).build(); // ?
  }

  @GetMapping("bad-request/{id}")
  public UserDto badRequest(@PathVariable String id) {
    try {
      Integer.parseUnsignedInt(id);
      return new UserDto("address", "name", LocalDate.of(2020, 01, 01));
    } catch (NumberFormatException e) {
      throw new InputValidationException(
          Error.INVALID_USER_ID, "UserID must be numeric");
    }
  }

  @GetMapping("uncaught-test")
  public UserDto throwUncaughtException() {
    throw new IllegalStateException();
  }

}
