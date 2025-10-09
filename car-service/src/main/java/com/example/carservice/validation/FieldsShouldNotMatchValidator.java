package com.example.carservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsShouldNotMatchValidator implements ConstraintValidator<FieldsShouldNotMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(FieldsShouldNotMatch constraintAnnotation) {
        this.firstFieldName = constraintAnnotation.firstField();
        this.secondFieldName = constraintAnnotation.secondField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object firstFieldValue = new BeanWrapperImpl(value).getPropertyValue(firstFieldName);
        Object secondFieldValue = new BeanWrapperImpl(value).getPropertyValue(secondFieldName);

        return firstFieldValue == null || !firstFieldValue.equals(secondFieldValue);
    }
}