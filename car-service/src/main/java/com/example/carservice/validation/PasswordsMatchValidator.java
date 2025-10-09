package com.example.carservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl; // Import Spring's BeanWrapper

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;
    private String message;

    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
        // Get the field names from the annotation
        this.firstFieldName = constraintAnnotation.first();
        this.secondFieldName = constraintAnnotation.second();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        // Use Spring's BeanWrapper to get the property values from the object
        Object firstFieldValue = new BeanWrapperImpl(value).getPropertyValue(firstFieldName);
        Object secondFieldValue = new BeanWrapperImpl(value).getPropertyValue(secondFieldName);

        // Check if the fields are equal (handles nulls correctly)
        boolean areEqual = (firstFieldValue == null && secondFieldValue == null) ||
                (firstFieldValue != null && firstFieldValue.equals(secondFieldValue));

        if (!areEqual) {
            // If they are not equal, disable the default violation and create a new one
            context.disableDefaultConstraintViolation();
            // Attach the error message to the second field (e.g., the confirmation field)
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(secondFieldName)
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}