package com.example.oragnization.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy =PhonenumberValidator.class)
public @interface Phonenumber {
String message() default "Invalid Phone number";
Class<?>[] groups() default{};
Class<? extends Payload>[] payload() default{};
}
