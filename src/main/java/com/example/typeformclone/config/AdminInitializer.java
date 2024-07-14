package com.example.typeformclone.config;

import com.example.typeformclone.model.User;
import com.example.typeformclone.repository.UserRepository;
import com.example.typeformclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@Configuration
public class AdminInitializer {

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;



    @PostConstruct
    public void init() {
        if (!userRepository.findByUsername(adminUsername).isPresent()) {
            User admin = new User();
            admin.setUsername(adminUsername);
            admin.setPassword(adminPassword);
            admin.setRole("ADMIN");
            userService.save(admin);
            System.out.println("Admin user created with username: " + adminUsername + " and encoded password: " + admin.getPassword());
        } else {
            System.out.println("Admin user already exists.");
        }
    }
}
