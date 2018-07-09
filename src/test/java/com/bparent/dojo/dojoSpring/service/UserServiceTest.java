package com.bparent.dojo.dojoSpring.service;

import com.bparent.dojo.dojoSpring.dto.UserDto;
import com.bparent.dojo.dojoSpring.model.Todo;
import com.bparent.dojo.dojoSpring.model.User;
import com.bparent.dojo.dojoSpring.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addTodoToUser_shouldReturnAUserWith3Todos() {
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        when(userRepository.findById(3)).thenReturn(Optional.of(
                User.builder()
                .todos(new ArrayList<>(Arrays.asList(Todo.builder().build(), Todo.builder().build())))
                .build()
                ));

        UserDto userDto = userService.addTodoToUser(3, "New Todo");
        assertEquals(3, userDto.getTodos().size());
        assertEquals("New Todo", userDto.getTodos().get(2).getText());

        verify(userRepository).save(userCaptor.capture());
        User userSavedInDb = userCaptor.getValue();
        assertEquals(3, userSavedInDb.getTodos().size());
        assertEquals("New Todo", userSavedInDb.getTodos().get(2).getText());
    }

    @Test(expected=NullPointerException.class)
    public void changeTodoStatus_shouldThrowAnExceptionIfIdNotFound() {
        when(userRepository.findById(3)).thenReturn(Optional.empty());

        userService.addTodoToUser(3, "New Todo");
        fail("Should have raise an exception");
    }


    @Test
    public void addTodoToUser_shouldCallDbWithFilteredTodos() {
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        when(userRepository.findById(3)).thenReturn(Optional.of(
                User.builder()
                        .todos(new ArrayList<>(Arrays.asList(
                                Todo.builder().id(1).build(),
                                Todo.builder().id(2).build(),
                                Todo.builder().id(3).build())))
                        .build()
        ));

        userService.deleteTodoFromUser(3, 2);

        verify(userRepository).save(userCaptor.capture());
        User userSavedInDb = userCaptor.getValue();
        assertEquals(2, userSavedInDb.getTodos().size());
        assertTrue(userSavedInDb.getTodos().stream().anyMatch(todo -> todo.getId() == 1));
        assertTrue(userSavedInDb.getTodos().stream().anyMatch(todo -> todo.getId() == 3));
    }

}