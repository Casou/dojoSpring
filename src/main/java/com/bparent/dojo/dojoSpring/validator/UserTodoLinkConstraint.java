package com.bparent.dojo.dojoSpring.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserTodoLinkValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UserTodoLinkConstraint {

    String message() default "You cannot delete a todo that does'nt belong to you";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

