package com.tutorial.springsecurityjwt.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tutorial.springsecurityjwt.transaction.dto.Transaction;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private String userId;

    @Column(name = "user_name", unique = true)
    @NonNull
    private String username;
    @Column(name = "name")
    private String name;
    @Column(name= "surname")
    private String surname;
    @NonNull
    @Column(name = "email", unique = true)
    private String email;

    @NonNull
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Transaction> transaction;

    public User() {
    }

    public User(String userId, @NonNull String username, @NonNull String email, @NonNull String password, Set<Transaction> transaction) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.transaction = transaction;
    }
    public User(@NonNull String username,@NonNull String password) {
        this.username = username;
        this.password = password;
    }
   // public User(Set<Transaction> transaction) {
   //     this.transaction = transaction;
   // }

    public User(UserDto.UserRequest userRequest) {
        this.email = userRequest.email();
        this.surname = userRequest.surname();
        this.name = userRequest.name();
        this.password = userRequest.password();
        this.username = userRequest.username();
        //this.transaction = userRequest.transaction();

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(Set<Transaction> transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}

