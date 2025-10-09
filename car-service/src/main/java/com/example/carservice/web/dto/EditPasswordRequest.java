package com.example.carservice.web.dto;

import com.example.carservice.validation.FieldsShouldNotMatch;
import com.example.carservice.validation.PasswordsMatch;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@PasswordsMatch(first = "newPassword", second = "confirmNewPassword", message = "The new passwords do not match.")
@FieldsShouldNotMatch(firstField = "currentPassword", secondField = "newPassword", message = "New password cannot be the same as the current password.")
public class EditPasswordRequest {

    private String currentPassword;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
            message = "Password must be at least 6 characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character")
    private String newPassword;

    @NotBlank
    private String confirmNewPassword;
}
