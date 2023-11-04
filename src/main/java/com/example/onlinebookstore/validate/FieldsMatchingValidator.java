package com.example.onlinebookstore.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsMatchingValidator
        implements ConstraintValidator<FieldsMatching, Object> {
    private String field;
    private String fieldMatchWith;

    @Override
    public void initialize(FieldsMatching constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatchWith = constraintAnnotation.fieldMatchWith();
    }

    @Override
    public boolean isValid(
            Object object, ConstraintValidatorContext constraintValidatorContext) {
        Object fieldValue = new BeanWrapperImpl(object)
                .getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(object)
                .getPropertyValue(fieldMatchWith);

        if (fieldValue != null) {
            return fieldValue.equals(fieldMatchValue);
        } else {
            return fieldMatchValue == null;
        }
    }
}
