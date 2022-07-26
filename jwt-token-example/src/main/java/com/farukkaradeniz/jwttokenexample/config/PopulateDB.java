package com.farukkaradeniz.jwttokenexample.config;

import com.farukkaradeniz.jwttokenexample.dto.User;
import com.farukkaradeniz.jwttokenexample.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PopulateDB implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public PopulateDB(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        addUser(new User("user1", passwordEncoder.encode("user1pw"), "abc1"));
        addUser(new User("user2", passwordEncoder.encode("user2pw"), "abc2"));
        addUser(new User("user3", passwordEncoder.encode("user3pw"), "abc3"));
    }

    private void addUser(User user) {
        System.out.printf("Adding user: %s%n", user.getUsername());
        userRepository.save(user);
    }
}
