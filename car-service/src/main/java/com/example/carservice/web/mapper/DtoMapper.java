package com.example.carservice.web.mapper;

import com.example.carservice.user.model.User;
import com.example.carservice.web.dto.EditProfileRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DtoMapper {

    public static EditProfileRequest fromUser(User user) {
        return EditProfileRequest.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .country(user.getCountry())
                .profilePicture(user.getProfilePicture())
                .build();
    }
}
