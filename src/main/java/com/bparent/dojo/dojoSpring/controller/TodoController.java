package com.bparent.dojo.dojoSpring.controller;

import com.bparent.dojo.dojoSpring.model.Todo;
import com.bparent.dojo.dojoSpring.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/todo")
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

}
