package com.bparent.dojo.dojoSpring.controller;

import com.bparent.dojo.dojoSpring.model.Todo;
import com.bparent.dojo.dojoSpring.repository.TodoRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TodoControllerTest {

    @InjectMocks
    private TodoController todoController;

    @Mock
    private TodoRepository todoRepository;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(todoController).build();
    }

    @Test
    public void findAll_shouldReturn2Records() throws Exception {
        when(todoRepository.findAll()).thenReturn(Arrays.asList(
                Todo.builder().id(1).text("Todo 1").build(),
                Todo.builder().id(2).text("Todo 2").build()
        ));

        MvcResult mvcResult = this.mockMvc.perform(get("/todo"))
                .andExpect(status().isOk())
                .andReturn();

        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        assertEquals(2, jsonArray.length());

        assertEquals(1, ((JSONObject) jsonArray.get(0)).get("id"));
        assertEquals("Todo 1", ((JSONObject) jsonArray.get(0)).get("text"));
        assertEquals(2, ((JSONObject) jsonArray.get(1)).get("id"));
        assertEquals("Todo 2", ((JSONObject) jsonArray.get(1)).get("text"));
    }

}