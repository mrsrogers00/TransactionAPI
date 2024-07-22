package com.tutorial.springsecurityjwt.user.controller;

import com.tutorial.springsecurityjwt.exceptions.UserNotFoundException;
import com.tutorial.springsecurityjwt.user.dto.User;
import com.tutorial.springsecurityjwt.user.dto.UserDto;
import com.tutorial.springsecurityjwt.user.service.UserServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Api(value = "Transaction API", description = "CRUD operations for User")
@Validated
public class UserRestController {

    @Autowired
    UserServiceImp userService;

    @PostMapping()
    @ApiOperation(value = "signUp a user", response = ResponseEntity.class)
    public ResponseEntity<?> signUp(@RequestBody UserDto.UserRequest userLogin) throws IllegalAccessException{
        UserDto.UserRequest user = userService.saveUserInformation(userLogin);
        UserDto.Response response = new UserDto.Response("User logged in successfully", user);
        return ResponseEntity.ok(response);
    }
    @GetMapping()
    @ApiOperation(value = "signUp a user", response = ResponseEntity.class)
    public ResponseEntity<List<UserDto.Response>> getAllUsers() {
        List<UserDto.Response> users = userService.getUserInformations().stream()
                .map(user -> {
                    UserDto.UserRequest userRequest = new UserDto.UserRequest(user.getUsername(),user.getName(),user.getSurname(), user.getEmail(), user.getPassword()/*, user.getTransaction()*/); // User'dan UserRequest'e dönüştürme
                    return new UserDto.Response("Users listed successfully", userRequest);
                })
                .toList();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{userId}")
    @ApiOperation(value = "signUp a user", response = ResponseEntity.class)
    public ResponseEntity<?> getOneUser(@PathVariable String userId) {
        User user = userService.getUserInformationById(userId);
        if(user == null) {
            throw new UserNotFoundException();
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}")
    @ApiOperation(value = "signUp a user", response = ResponseEntity.class)
    public ResponseEntity<Void> updateOneUser(@PathVariable String userId, @RequestBody User newUser) {
        User user = userService.updateUserInformation(userId, newUser);
        if(user != null)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @DeleteMapping("/{userId}")
    @ApiOperation(value = "Delete a person by ID")
    public void deleteOneUser(@PathVariable String userId) {
        userService.deleteUserInformation(userId);
    }



}
