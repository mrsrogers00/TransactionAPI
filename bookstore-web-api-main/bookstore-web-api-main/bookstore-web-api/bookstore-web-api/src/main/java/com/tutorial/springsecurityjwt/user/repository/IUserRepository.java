package com.tutorial.springsecurityjwt.user.repository;

import com.tutorial.springsecurityjwt.user.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
        Optional<User> findByUsername(String username);

        //User findById(String id);

        List<User> findAll();

        User save(User user);

        void deleteById(String id);

}