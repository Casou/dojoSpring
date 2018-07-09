package com.bparent.dojo.dojoSpring.controller;

import com.bparent.dojo.dojoSpring.dto.UserDto;
import com.bparent.dojo.dojoSpring.model.User;
import com.bparent.dojo.dojoSpring.repository.UserRepository;
import com.bparent.dojo.dojoSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(user -> new UserDto().toDto(user))
                .peek(userDto -> userDto.setTodos(null))
                .collect(Collectors.toList());
    }

    @GetMapping("/users/name/{name}")
    public List<UserDto> findAllAndReplaceName(@PathVariable String name) {
        return userRepository.findAll().stream()
                .map(user -> new UserDto().toDto(user))
                .peek(userDto -> userDto.setName(name))
                .collect(Collectors.toList());
    }

    @GetMapping("/users/withTodo")
    public List<UserDto> findAllWithTodos() {
        return userRepository.findAll().stream()
                .map(user -> new UserDto().toDto(user))
                .collect(Collectors.toList());
    }

    @PostMapping("/users/todo")
    public UserDto addTodoToUser(@RequestBody UserDto userDto) {
        return userService.addTodoToUser(userDto.getId(), userDto.getTodos().get(0).getText());
    }


}
