// SPDX-FileCopyrightText: 2025 diggsweden/wallet-backend-reference
//
// SPDX-License-Identifier: EUPL-1.2

package com.example.demo;

import org.springframework.boot.SpringApplication;

public class TestDemoApplication {

  public static void main(String[] args) {
    SpringApplication.from(DemoApplication::main).with(TestcontainersConfiguration.class).run(args);
  }

}
