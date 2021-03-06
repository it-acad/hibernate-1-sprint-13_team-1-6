package com.softserve.itacademy.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TodoTest {

    @Test
    void createValidTodo() {
        ToDo validTodo = new ToDo();
        validTodo.setOwner(new User());
        validTodo.setTasks(new ArrayList<>());
        validTodo.setTitle("a");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> violations = validator.validate(validTodo);

        assertEquals(0, violations.size());
    }

    @Test
    void titleConstraintViolation() {

        ToDo validTodo = new ToDo();
        validTodo.setOwner(new User());
        validTodo.setTasks(new ArrayList<>());
        validTodo.setTitle(" ");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> violations = validator.validate(validTodo);

        assertEquals(1, violations.size());
    }
}
