package com.bparent.dojo.dojoSpring.dto;

import com.bparent.dojo.dojoSpring.model.Todo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodoDto extends AbstractDto<Todo> {

    private Integer id;
    private String text;

    @Override
    public TodoDto toDto(Todo entity) {
        return (TodoDto) super.toDto(entity);
    }

}
