package com.bparent.dojo.dojoSpring.service;

import com.bparent.dojo.dojoSpring.dto.UserDto;
import com.bparent.dojo.dojoSpring.model.Todo;
import com.bparent.dojo.dojoSpring.model.User;
import com.bparent.dojo.dojoSpring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto addTodoToUser(Integer idUser, String newTodoText) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new NullPointerException("User not found for id : " + idUser));
        user.getTodos().add(Todo.builder().user(user).text(newTodoText).build());

        userRepository.save(user);

        return new UserDto().toDto(user);
    }

    public void deleteTodoFromUser(Integer idUser, Integer idTodo) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new NullPointerException("User not found for id : " + idUser));
        user.getTodos().remove(user.getTodos().stream().filter(todo -> todo.getId().equals(idTodo)).findFirst().get());

        userRepository.save(user);
    }

}
