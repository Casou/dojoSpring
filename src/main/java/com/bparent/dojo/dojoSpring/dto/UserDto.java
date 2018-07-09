package com.bparent.dojo.dojoSpring.dto;

import com.bparent.dojo.dojoSpring.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto extends AbstractDto<User> {

    private Integer id;
    private String login;
    private String name;
    private String firstName;
    private String job;
    @Valid
    private List<TodoDto> todos;

    @Override
    public UserDto toDto(User entity) {
        return (UserDto) super.toDto(entity);
    }

}
