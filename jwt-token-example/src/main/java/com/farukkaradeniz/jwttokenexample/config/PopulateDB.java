package com.farukkaradeniz.jwttokenexample.config;

import com.farukkaradeniz.jwttokenexample.dto.Role;
import com.farukkaradeniz.jwttokenexample.dto.User;
import com.farukkaradeniz.jwttokenexample.repository.RoleRepository;
import com.farukkaradeniz.jwttokenexample.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PopulateDB implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public PopulateDB(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        var roleUser = new Role(1, "USER");
        var roleAdmin = new Role(2, "ADMIN");
        var roleGod = new Role(3, "GOD");
        addRole(roleUser);
        addRole(roleAdmin);
        addRole(roleGod);

        addUser(new User("user1", passwordEncoder.encode("user1pw"), "abc1", Set.of(roleUser)));
        addUser(new User("user2", passwordEncoder.encode("user2pw"), "abc2", Set.of(roleAdmin, roleGod)));
        addUser(new User("user3", passwordEncoder.encode("user3pw"), "abc3", Set.of(roleAdmin)));
    }

    private void addUser(User user) {
        System.out.printf("Adding user: %s%n", user.getUsername());
        userRepository.save(user);
    }

    private void addRole(Role role) {
        System.out.printf("Adding role: %s%n", role.getName());
        roleRepository.save(role);

    }
}
