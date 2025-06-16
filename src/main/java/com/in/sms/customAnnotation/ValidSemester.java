package com.in.sms.customAnnotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SemesterValidator.class)
public @interface ValidSemester {

    String message() default "Semester must be a number between 1 and 8";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
