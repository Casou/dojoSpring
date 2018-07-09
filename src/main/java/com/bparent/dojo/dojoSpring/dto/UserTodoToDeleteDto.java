package com.bparent.dojo.dojoSpring.dto;

import com.bparent.dojo.dojoSpring.model.User;
import com.bparent.dojo.dojoSpring.validator.UserTodoLinkConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@UserTodoLinkConstraint
public class UserTodoToDeleteDto extends AbstractDto<User> {

    private Integer idUser;
    private Integer idTodo;

}
