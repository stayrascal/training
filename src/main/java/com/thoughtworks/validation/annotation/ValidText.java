package com.thoughtworks.validation.annotation;

import com.thoughtworks.validation.validator.TextValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {TextValidator.class})
@Documented
public @interface ValidText {

    String field();

    String message() default " ";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
