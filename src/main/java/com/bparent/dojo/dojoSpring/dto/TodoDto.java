package com.bparent.dojo.dojoSpring.dto;

import com.bparent.dojo.dojoSpring.model.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDto extends AbstractDto<Todo> {

    private Integer id;
    private String text;
    private Boolean complete;

    @Override
    public TodoDto toDto(Todo entity) {
        return (TodoDto) super.toDto(entity);
    }

}
