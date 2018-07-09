package com.bparent.dojo.dojoSpring.validator;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TodoTextValidator implements ConstraintValidator<TodoTextConstraint, String> {

    @Override
    public void initialize(TodoTextConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String textField,
                           ConstraintValidatorContext cxt) {
        return textField == null ||
                (StringUtils.countOccurrencesOf(textField, "N") <= 2
                && StringUtils.countOccurrencesOf(textField, "T") <= 4);
    }

}
