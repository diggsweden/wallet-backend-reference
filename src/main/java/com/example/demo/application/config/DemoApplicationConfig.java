// SPDX-FileCopyrightText: 2025 diggsweden/wallet-backend-reference
//
// SPDX-License-Identifier: EUPL-1.2

package com.example.demo.application.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("wallet")
public record DemoApplicationConfig() {
  // record if possible
}
