package com.example.demo.infrastructure.spring.data;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.infrastructure.model.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {

}
