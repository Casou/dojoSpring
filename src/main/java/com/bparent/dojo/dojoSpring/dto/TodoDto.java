package com.bparent.dojo.dojoSpring.dto;

import com.bparent.dojo.dojoSpring.model.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDto extends AbstractDto<Todo> {

    private Integer id;
    @Size(max = 50, message = "The maximum length of a todo is 50 caracters.")
    private String text;
    private Boolean complete;

    @Override
    public TodoDto toDto(Todo entity) {
        return (TodoDto) super.toDto(entity);
    }

}
