package com.example.onlinebookstore.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FieldsMatchingValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsMatching {
    String field();

    String fieldMatchWith();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String message() default "Fields do not match!";
}
