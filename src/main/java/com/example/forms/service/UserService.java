package com.example.forms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.forms.model.User;
import com.example.forms.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Save or Update user
    public void saveUser(User user){
        userRepository.save(user);
    }

    // Get all users for the dashboard
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // Find a single user (for the Edit button)
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Remove a user (for the Delete button)
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}