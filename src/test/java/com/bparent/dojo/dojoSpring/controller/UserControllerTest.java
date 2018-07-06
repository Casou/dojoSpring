package com.bparent.dojo.dojoSpring.controller;

import com.bparent.dojo.dojoSpring.model.Todo;
import com.bparent.dojo.dojoSpring.model.User;
import com.bparent.dojo.dojoSpring.repository.TodoRepository;
import com.bparent.dojo.dojoSpring.repository.UserRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

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

        MvcResult mvcResult = this.mockMvc.perform(get("/todo"))
                .andExpect(status().isOk())
                .andReturn();

        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        assertEquals(2, jsonArray.length());

        assertEquals(1, ((JSONObject) jsonArray.get(0)).get("id"));
        assertEquals("User 1", ((JSONObject) jsonArray.get(0)).get("login"));
        assertEquals(2, ((JSONObject) jsonArray.get(1)).get("id"));
        assertEquals("User 2", ((JSONObject) jsonArray.get(1)).get("login"));
    }

}