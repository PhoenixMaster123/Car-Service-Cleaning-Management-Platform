package com.example.carservice.user.service;

import com.example.carservice.common.exception.UserEmailAlreadyExistsException;
import com.example.carservice.common.exception.UserEmailDoesNotExistException;
import com.example.carservice.common.exception.UserPasswordDoesNotMatchException;
import com.example.carservice.user.model.User;
import com.example.carservice.user.model.UserRole;
import com.example.carservice.user.repository.UserRepository;
import com.example.carservice.web.dto.EditProfileRequest;
import com.example.carservice.web.dto.EditVehicleRequest;
import com.example.carservice.web.dto.LoginRequest;
import com.example.carservice.web.dto.RegisterRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

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
        Optional<User> userOpt = userRepository.findByEmail(loginRequest.getEmail());

        if (userOpt.isEmpty()) {
            log.warn("Login attempt with non-existing email: {}", loginRequest.getEmail());
            throw new UserEmailDoesNotExistException("Invalid email or password");
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            log.warn("Invalid password attempt for email: {}", loginRequest.getEmail());
            throw new UserPasswordDoesNotMatchException("Invalid email or password");
        }

        log.info("User logged in: {}", user.getEmail());
        return user;
    }

    @Transactional
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

    public User getById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserEmailDoesNotExistException("User not found with id: " + id));
    }

    public void updateProfile(UUID id, EditProfileRequest editProfileRequest) {
        User user = getById(id);

        user.setFirstName(editProfileRequest.getFirstName());
        user.setLastName(editProfileRequest.getLastName());
        user.setEmail(editProfileRequest.getEmail());
        user.setPhoneNumber(editProfileRequest.getPhoneNumber());
        user.setCountry(editProfileRequest.getCountry());
        user.setProfilePicture(editProfileRequest.getProfilePicture());

        userRepository.save(user);

        log.info("User profile updated: {}", user.getEmail());
    }
}
