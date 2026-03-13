package com.example.forms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String section;
    private String branch;
    private String frontendLink;
    private String backendLink;
    private String githubLink;

    // Standard ID getter/setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    public String getFrontendLink() { return frontendLink; }
    public void setFrontendLink(String frontendLink) { this.frontendLink = frontendLink; }

    public String getBackendLink() { return backendLink; }
    public void setBackendLink(String backendLink) { this.backendLink = backendLink; }

    public String getGithubLink() { return githubLink; }
    public void setGithubLink(String githubLink) { this.githubLink = githubLink; }
}