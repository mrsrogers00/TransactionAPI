package com.tutorial.springsecurityjwt.user.service;

import com.tutorial.springsecurityjwt.user.dto.User;
import com.tutorial.springsecurityjwt.user.dto.UserDto;

import java.util.List;

public interface IUserService {
    User getUserInformationById(String id);

    //User signIn(String username, String password);
    List<User> getUserInformations();

    UserDto.UserRequest saveUserInformation(UserDto.UserRequest userInformationDTO);

    User updateUserInformation(String id, User userInformationDTO);

    void deleteUserInformation(String id);
}

