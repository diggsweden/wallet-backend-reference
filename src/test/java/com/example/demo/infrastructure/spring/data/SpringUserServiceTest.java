package com.example.demo.infrastructure.spring.data;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.example.demo.domain.model.User;
import com.example.demo.infrastructure.model.UserEntity;

public class SpringUserServiceTest {
    
    private final UserRepository repository = mock(UserRepository.class);
    private final SpringUserService subject = new SpringUserService(repository);

    @Test
    void findsUserById() {
        UserEntity user = new UserEntity();
        UUID id = UUID.randomUUID();
        user.setId(id);
        user.setName("Alice");
        user.setAddress("Main street");
        user.setBirthDate(LocalDate.of(2001, 01, 23));
        
        given(repository.findById(id)).willReturn(Optional.of(user));

        // When
        User actual = subject.get(id);

        // Then
        assertThat(actual.id()).isEqualTo(id);
        assertThat(actual.name()).isEqualTo("Alice");
        assertThat(actual.address()).isEqualTo("Main street");
        assertThat(actual.birthDate()).isEqualTo(LocalDate.of(2001, 01, 23));
    }
}
