package com.example.carservice.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = PasswordsMatchValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordsMatch {

    // Add these two fields
    String first();
    String second();

    String message() default "The two fields do not match";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    // This allows you to group multiple annotations of the same type
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        PasswordsMatch[] value();
    }
}