package com.bparent.dojo.dojoSpring.validator;

import com.bparent.dojo.dojoSpring.dto.UserTodoToDeleteDto;
import com.bparent.dojo.dojoSpring.model.Todo;
import com.bparent.dojo.dojoSpring.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UserTodoLinkValidator implements ConstraintValidator<UserTodoLinkConstraint, UserTodoToDeleteDto> {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public void initialize(UserTodoLinkConstraint contactNumber) {
    }

    @Override
    public boolean isValid(UserTodoToDeleteDto userTodoDto,
                           ConstraintValidatorContext cxt) {
        Todo todo = todoRepository.findById(userTodoDto.getIdTodo()).orElseThrow(() -> new NullPointerException());
        return todo.getUser().getId().equals(userTodoDto.getIdUser());
    }

}
