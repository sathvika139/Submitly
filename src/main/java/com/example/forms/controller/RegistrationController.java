package com.example.forms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.forms.model.User;
import com.example.forms.service.UserService;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    // IMPORTANT: This prevents the error when you first open the page
    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("user", new User()); 
        return "index";
    }

    @PostMapping("/submit")
    public String submitForm(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "dashboard";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        // Fallback to new User if ID not found to prevent crashes
        model.addAttribute("user", (user != null) ? user : new User());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/dashboard";
    }
}