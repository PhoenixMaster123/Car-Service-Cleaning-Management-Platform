package com.example.carservice.user.service;

import com.example.carservice.common.exception.UserEmailAlreadyExistsException;
import com.example.carservice.common.exception.UserEmailDoesNotExistException;
import com.example.carservice.common.exception.UserPasswordDoesNotMatchException;
import com.example.carservice.user.model.User;
import com.example.carservice.user.model.UserRole;
import com.example.carservice.user.repository.UserRepository;
import com.example.carservice.web.dto.LoginRequest;
import com.example.carservice.web.dto.RegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(LoginRequest loginRequest) {
        Optional<User> userOpt = userRepository.findByEmail(loginRequest.email());

        if (userOpt.isEmpty()) {
            log.warn("Login attempt with non-existing email: {}", loginRequest.email());
            throw new UserEmailDoesNotExistException("Invalid email or password");
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            log.warn("Invalid password attempt for email: {}", loginRequest.email());
            throw new UserPasswordDoesNotMatchException("Invalid email or password");
        }

        log.info("User logged in: {}", user.getEmail());
        return user;
    }

    public void register(RegisterRequest registerRequest) {

        Optional<User> existingUser = userRepository.findByEmail(registerRequest.getEmail());

        if (existingUser.isPresent()) {
            throw new UserEmailAlreadyExistsException("Email already in use: " + registerRequest.getEmail());
        }

        User newUser = User.builder()
                .email(registerRequest.getEmail())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(UserRole.USER)
                .build();

        userRepository.save(newUser);

        log.info("New user registered: {}", newUser.getEmail());
    }
}
