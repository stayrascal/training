package com.thoughtworks.validation.validator;

import com.thoughtworks.validation.annotation.ValidText;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TextValidator implements ConstraintValidator<ValidText, String> {

    @Override
    public void initialize(ValidText constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.isEmpty(value) || value.contains("text");
    }

}
