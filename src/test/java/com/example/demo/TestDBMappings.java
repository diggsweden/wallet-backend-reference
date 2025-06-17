package com.example.demo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.example.demo.infrastructure.model.UserEntity;
import com.example.demo.infrastructure.repository.UserRepository;


@Import(TestcontainersConfiguration.class)
@SpringBootTest
class TestDBMappings {

    @Autowired
    UserRepository userRepository;

    @Test
    void testUserRepo() {
        UserEntity user =  new UserEntity();
        user.setAddress("Street 1");
        user.setBirthDate(LocalDate.of(2024, 01, 01));
        assertNull(user.getId());
        userRepository.save(user);
        assertNotNull(user.getId());
    }
}