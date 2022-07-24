package com.farukkaradeniz.jwttokenexample.config;

import com.farukkaradeniz.jwttokenexample.dto.User;
import com.farukkaradeniz.jwttokenexample.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostConstruct
    public void initialize() {
        addUser(new User("user1", "user1pw", "abc1"));
        addUser(new User("user2", "user2pw", "abc2"));
        addUser(new User("user3", "user3pw", "abc3"));
    }

    public User getUser(String userName) {
        return userRepository.findUserByUsername(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private void addUser(User user) {
        System.out.printf("Adding user: %s%n", user.getUsername());
        userRepository.save(user);
    }
}
