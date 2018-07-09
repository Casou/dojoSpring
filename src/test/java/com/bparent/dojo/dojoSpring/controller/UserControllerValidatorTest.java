package com.bparent.dojo.dojoSpring.controller;

import com.bparent.dojo.dojoSpring.dto.UserDto;
import com.bparent.dojo.dojoSpring.model.Todo;
import com.bparent.dojo.dojoSpring.model.User;
import com.bparent.dojo.dojoSpring.repository.TodoRepository;
import com.bparent.dojo.dojoSpring.repository.UserRepository;
import com.bparent.dojo.dojoSpring.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerValidatorTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void deleteTodoFromUser_shouldCallService() throws Exception {
//        when(todoRepository.findById(4)).thenReturn(Optional.of(Todo.builder()
//                .user(User.builder().id(3).build())
//                .build()));

        MvcResult mvcResult = this.mockMvc.perform(delete("/users/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(("{\"idUser\":1,\"idTodo\":4}"))
        )
                .andExpect(status().isOk())
                .andReturn();

//        verify(userService).deleteTodoFromUser(3, 4);
//        verifyNoMoreInteractions(userService);
    }

    @Test
    public void deleteTodoFromUser_shouldReturnForbiddenIfTodoDoesntBelongToUser() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(delete("/users/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(("{\"idUser\":1,\"idTodo\":9}"))
                )
                .andExpect(status().isForbidden())
                .andReturn();

    }

}