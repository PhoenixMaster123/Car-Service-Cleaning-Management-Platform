package com.example.carservice.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FieldsShouldNotMatchValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsShouldNotMatch {
    String message() default "Fields must not match";
    String firstField();
    String secondField();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}