package com.bparent.dojo.dojoSpring.service;

import com.bparent.dojo.dojoSpring.dto.TodoDto;
import com.bparent.dojo.dojoSpring.model.Todo;
import com.bparent.dojo.dojoSpring.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public TodoDto changeTodoStatus(Integer idTodo, Boolean complete) {
        Todo todoDb = todoRepository.findById(idTodo)
                .orElseThrow(() -> new NullPointerException("Todo not found for id : " + idTodo));

        todoDb.setComplete(complete);

        return new TodoDto().toDto(todoDb);
    }

}
