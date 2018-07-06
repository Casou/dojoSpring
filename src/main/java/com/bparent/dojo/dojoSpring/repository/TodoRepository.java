package com.bparent.dojo.dojoSpring.repository;

import com.bparent.dojo.dojoSpring.model.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(path = "todo")
public interface TodoRepository extends CrudRepository<Todo, Integer> {

    List<Todo> findAll();

    // http://localhost:8080/todo/search/findByTextContaining?text=ice
    List<Todo> findByTextContaining(@Param("text") String text);

}
