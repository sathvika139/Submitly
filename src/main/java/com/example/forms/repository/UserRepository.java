package com.example.forms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.forms.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}