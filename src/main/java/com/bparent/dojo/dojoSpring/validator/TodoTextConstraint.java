package com.bparent.dojo.dojoSpring.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TodoTextValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TodoTextConstraint {

    String message() default "Todo text should contains maximum 2 'N' and 4 'P'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

