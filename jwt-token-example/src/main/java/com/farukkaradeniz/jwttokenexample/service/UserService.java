package com.farukkaradeniz.jwttokenexample.service;

import com.farukkaradeniz.jwttokenexample.config.AppUserDetails;
import com.farukkaradeniz.jwttokenexample.dto.User;
import com.farukkaradeniz.jwttokenexample.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return AppUserDetails.build(getUser(username));
    }
}
