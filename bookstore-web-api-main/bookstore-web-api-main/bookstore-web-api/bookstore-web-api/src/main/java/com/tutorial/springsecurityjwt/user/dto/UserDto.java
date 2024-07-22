package com.tutorial.springsecurityjwt.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tutorial.springsecurityjwt.transaction.dto.Transaction;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;

import java.util.Set;

public class UserDto {

    public record UserRequest(String username, String name, String surname, String email, String password/*, Set<Transaction> transaction */){

    }


    public record Response(String message, UserRequest request){

    }
}
