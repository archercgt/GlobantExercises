package com.example.api_challenge_graphql.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private Long id;
    private String fullName;

    //Constructor Methods
    public User() {
    }

    public User(Long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    //Getter and Setter Methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
