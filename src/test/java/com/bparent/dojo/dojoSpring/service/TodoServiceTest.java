package com.bparent.dojo.dojoSpring.service;

import com.bparent.dojo.dojoSpring.dto.TodoDto;
import com.bparent.dojo.dojoSpring.model.Todo;
import com.bparent.dojo.dojoSpring.repository.TodoRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class TodoServiceTest {

    @InjectMocks
    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void changeTodoStatus_shouldReturnTdoWithStatusChanged() {
        when(todoRepository.findById(1)).thenReturn(Optional.of(
                Todo.builder().id(1).complete(false).build()
        ));

        TodoDto todoDto = todoService.changeTodoStatus(1, true);
        assertEquals(true, todoDto.getComplete());
    }

    @Test(expected=NullPointerException.class)
    public void changeTodoStatus_shouldThrowAnExceptionIfIdNotFound() {
        when(todoRepository.findById(1)).thenReturn(Optional.empty());

        todoService.changeTodoStatus(1, true);
        fail("Should have raise an exception");
    }

}