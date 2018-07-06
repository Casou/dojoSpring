package com.bparent.dojo.dojoSpring.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@DataJpaTest
@RunWith(SpringJUnit4ClassRunner.class)
@Sql({"/schema.sql", "/data.sql"})
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void findByTextContaining_shouldReturn3Records() {
        assertEquals(3, todoRepository.findByTextContaining("P").size());
    }

}