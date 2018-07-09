package com.bparent.dojo.dojoSpring.controller;

import com.bparent.dojo.dojoSpring.dto.TodoDto;
import com.bparent.dojo.dojoSpring.dto.UserDto;
import com.bparent.dojo.dojoSpring.model.Todo;
import com.bparent.dojo.dojoSpring.repository.TodoRepository;
import com.bparent.dojo.dojoSpring.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/todo")
    public List<TodoDto> findAll() {
        return todoRepository.findAll().stream()
                .map(todo -> new TodoDto().toDto(todo))
                .collect(Collectors.toList());
    }

    @PutMapping("/todo/complete")
    public TodoDto completeTodo(@RequestBody Todo todo) {
        return todoService.changeTodoStatus(todo.getId(), todo.getComplete());
    }

}
