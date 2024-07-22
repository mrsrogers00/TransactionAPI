package com.tutorial.springsecurityjwt.user.service;

import com.tutorial.springsecurityjwt.user.dto.User;
import com.tutorial.springsecurityjwt.user.dto.UserDto;
import com.tutorial.springsecurityjwt.user.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements IUserService {
    private final IUserRepository repository;

    public UserServiceImp(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getUserInformationById(String id) {
        Optional<User> optional = repository.findById(id);
        if(!optional.isPresent()){
            return null;
        }
        return optional.get(); // Block until the result is available
    }
    public Optional<User>  getOneUserByUserName(String userName) {
        return repository.findByUsername(userName);
    }

    @Override
    public List<User> getUserInformations() {
        return repository.findAll(); // Block until all items are collected into a list
    }

    @Override
    public UserDto.UserRequest saveUserInformation(UserDto.UserRequest userInformationDTO) {
        User user = repository.save(new User(userInformationDTO));
        return new UserDto.UserRequest(user.getUsername(), user.getName(), user.getSurname(), user.getEmail(), user.getPassword()/*, user.getTransaction()*/); // Block until the result is available
    }

    @Override
    public User updateUserInformation(String id, User userMono) {
        User user = null; // Block until the user is retrieved
        if (user != null) {
            userMono.setUserId(user.getUserId()); // Update user properties
            return repository.save(userMono); // Block until the result is available
        }
        return null; // Handle case where user is not found
    }

    @Override
    public void deleteUserInformation(String id) {
        repository.deleteById(id); // Block until the deletion operation is complete
    }
}
