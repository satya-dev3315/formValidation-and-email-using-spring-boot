package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.UserRegistration;

public interface UserRegistrationRepo extends JpaRepository<UserRegistration, Integer> {


}
