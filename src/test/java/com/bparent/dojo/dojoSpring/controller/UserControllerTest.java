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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TodoRepository todoRepository;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }


    @Test
    public void findAll_shouldReturn2Records() throws Exception {
        when(userRepository.findAll()).thenReturn(Arrays.asList(
                User.builder().id(1).login("User 1").build(),
                User.builder().id(2).login("User 2").build()
        ));

        MvcResult mvcResult = this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andReturn();

        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        assertEquals(2, jsonArray.length());

        assertEquals(1, ((JSONObject) jsonArray.get(0)).get("id"));
        assertEquals("User 1", ((JSONObject) jsonArray.get(0)).get("login"));
        assertEquals(2, ((JSONObject) jsonArray.get(1)).get("id"));
        assertEquals("User 2", ((JSONObject) jsonArray.get(1)).get("login"));
    }

    @Test
    public void findAllAndReplaceName_shouldReturn2RecordsWithASpecificName() throws Exception {
        when(userRepository.findAll()).thenReturn(Arrays.asList(
                User.builder().id(1).name("name").build(),
                User.builder().id(2).name("name").build()
        ));

        MvcResult mvcResult = this.mockMvc.perform(get("/users/name/toto"))
                .andExpect(status().isOk())
                .andReturn();

        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        assertEquals(2, jsonArray.length());

        assertEquals(1, ((JSONObject) jsonArray.get(0)).get("id"));
        assertEquals("toto", ((JSONObject) jsonArray.get(0)).get("name"));
        assertEquals(2, ((JSONObject) jsonArray.get(1)).get("id"));
        assertEquals("toto", ((JSONObject) jsonArray.get(1)).get("name"));
    }

    @Test
    public void findAllWithTodos_shouldReturn2Records() throws Exception {
        when(userRepository.findAll()).thenReturn(Arrays.asList(
                User.builder().id(1).login("User 1").todos(Arrays.asList(
                        Todo.builder().id(1).text("Todo 1").build(),
                        Todo.builder().id(2).text("Todo 2").build()
                )).build(),
                User.builder().id(2).login("User 2").todos(Arrays.asList(
                        Todo.builder().id(3).text("Todo 3").build()
                )).build()
        ));

        MvcResult mvcResult = this.mockMvc.perform(get("/users/withTodo"))
                .andExpect(status().isOk())
                .andReturn();

        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        assertEquals(2, jsonArray.length());

        assertEquals(1, ((JSONObject) jsonArray.get(0)).get("id"));
        assertEquals("User 1", ((JSONObject) jsonArray.get(0)).get("login"));
        assertEquals(2, ((JSONArray) ((JSONObject) jsonArray.get(0)).get("todos")).length());
        assertEquals(1, ((JSONObject) ((JSONArray) ((JSONObject) jsonArray.get(0)).get("todos")).get(0)).get("id"));
        assertEquals("Todo 1", ((JSONObject) ((JSONArray) ((JSONObject) jsonArray.get(0)).get("todos")).get(0)).get("text"));
        assertEquals(2, ((JSONObject) ((JSONArray) ((JSONObject) jsonArray.get(0)).get("todos")).get(1)).get("id"));
        assertEquals("Todo 2", ((JSONObject) ((JSONArray) ((JSONObject) jsonArray.get(0)).get("todos")).get(1)).get("text"));

        assertEquals(2, ((JSONObject) jsonArray.get(1)).get("id"));
        assertEquals("User 2", ((JSONObject) jsonArray.get(1)).get("login"));
        assertEquals(1, ((JSONArray) ((JSONObject) jsonArray.get(1)).get("todos")).length());
        assertEquals(3, ((JSONObject) ((JSONArray) ((JSONObject) jsonArray.get(1)).get("todos")).get(0)).get("id"));
        assertEquals("Todo 3", ((JSONObject) ((JSONArray) ((JSONObject) jsonArray.get(1)).get("todos")).get(0)).get("text"));
    }


    @Test
    public void addTodoToUser_shouldReturnAUserDtoReturnedByTheService() throws Exception {
        when(userService.addTodoToUser(3, "New Todo")).thenReturn(UserDto.builder().id(3).build());

        MvcResult mvcResult = this.mockMvc.perform(post("/users/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(("{\"id\":3,\"todos\":[{\"text\":\"New Todo\"}]}"))
                )
                .andExpect(status().isOk())
                .andReturn();

        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals(3, jsonObject.get("id"));

        verify(userService).addTodoToUser(3, "New Todo");
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void addTodoToUser_shouldReturnAnErrorIfTextIsTooLong() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/users/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(("{\"id\":3,\"todos\":[{\"text\":\"Un meessage de plus de 50 caractères devrait faire se déclencher une exception dans le controller\"}]}"))
        )
                .andExpect(status().isBadRequest())
                .andReturn();

        verifyZeroInteractions(userService);
    }

    @Test
    public void addTodoToUser_shouldReturnAnErrorIfTooManyN() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/users/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(("{\"id\":3,\"todos\":[{\"text\":\"Too many NNN\"}]}"))
        )
                .andExpect(status().isBadRequest())
                .andReturn();

        verifyZeroInteractions(userService);
    }

    @Ignore
    @Test
    public void deleteTodoFromUser_shouldCallService() throws Exception {
        when(todoRepository.findById(4)).thenReturn(Optional.of(Todo.builder()
                .user(User.builder().id(3).build())
                .build()));

        MvcResult mvcResult = this.mockMvc.perform(delete("/users/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(("{\"idUser\":3,\"idTodo\":4}"))
        )
                .andExpect(status().isOk())
                .andReturn();

        verify(userService).deleteTodoFromUser(3, 4);
        verifyNoMoreInteractions(userService);
    }

}