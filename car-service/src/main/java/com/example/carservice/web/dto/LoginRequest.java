package com.example.carservice.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    String email;

    @NotBlank(message = "Password cannot be empty")
    String password;
}
