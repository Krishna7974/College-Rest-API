package com.in.sms.customAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber,String> {

    private static final String PHONE_PATTERN="^(\\+91[-\\s]?)?[6-9]\\d{9}$";

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s==null) return false;
        return s.matches(PHONE_PATTERN);
    }
}
