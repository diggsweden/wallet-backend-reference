// SPDX-FileCopyrightText: 2025 diggsweden/wallet-backend-reference
//
// SPDX-License-Identifier: EUPL-1.2

package com.example.demo.application.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
class UserControllerTest {
   
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    UserController mockController;

    @Test
    void noAutorizationTest() throws Exception {
        when(mockController.getUser(any())).thenReturn(null);
        String path =  "/users/1";
        this.mockMvc.perform(get(path)).andExpect(status().isUnauthorized());
     
    }
}
