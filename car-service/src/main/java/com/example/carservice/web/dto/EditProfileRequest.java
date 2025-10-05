package com.example.carservice.web.dto;

import com.example.carservice.user.model.Country;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditProfileRequest {

    @Size(min = 2, max = 30)
    private String firstName;

    @Size(min = 2, max = 30)
    private String lastName;

    @Email(
            regexp = "^$|^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
            message = "Please provide a valid email address"
    )
    private String email;

    @Pattern(
            regexp = "^$|(\\+\\d{1,3}[- ]?)?\\(?\\d{3}\\)?[- ]?\\d{3}[- ]?\\d{4}$",
            message = "Invalid phone number format."
    )
    private String phoneNumber;

    private Country country;

    @URL(message = "Please provide a valid URL.")
    private String profilePicture;
}
