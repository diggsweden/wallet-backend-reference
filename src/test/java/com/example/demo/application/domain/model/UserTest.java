package com.example.demo.application.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.example.demo.domain.model.User;
import com.example.demo.domain.model.UserBuilder;

class UserTest {

    @Test
    void testUserValidation() {
        LocalDate testDate =  LocalDate.of(2022, 01, 01);
        assertThrows(NullPointerException.class, () -> new User(UUID.randomUUID(), "Hello", null, testDate));

        UserBuilder builder =   UserBuilder.builder()
        .address("Street 1")
        .birthDate(testDate);
        
        assertThrows(NullPointerException.class, builder::build);
        
        builder.name("MyName");
        User user =  builder.build();
        assertThat(user.name()).isEqualTo("MyName");
        //verify immutable properties    
        testDate.plusYears(10);
        assertThat(user.birthDate()).isEqualTo(LocalDate.of(2022, 01, 01));
    }
    
}
