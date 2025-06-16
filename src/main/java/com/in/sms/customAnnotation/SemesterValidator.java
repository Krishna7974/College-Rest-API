package com.in.sms.customAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SemesterValidator implements ConstraintValidator<ValidSemester,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if(s==null && s.isBlank()) return false;
            int sem=Integer.parseInt(s.trim());
            return sem>=1 && sem<=8;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
