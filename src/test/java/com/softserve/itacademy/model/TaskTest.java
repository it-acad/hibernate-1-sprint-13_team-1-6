package com.softserve.itacademy.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskTest {

    private static ToDo todo;
    private static State state;

    @BeforeAll
    static void init(){
        todo = new ToDo();
        state = new State();
    }

    @Test
    void createValidTask() {
        Task task = new Task();
        task.setPriority(Priority.HIGH);
        task.setState(state);
        task.setTodo(todo);
        task.setName("Some Valid Name");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Task>> violations = validator.validate(task);

        assertEquals(0, violations.size());
    }

    @Test
    void nameConstraintViolation() {
        Task task = new Task();
        task.setPriority(Priority.HIGH);
        task.setState(state);
        task.setTodo(todo);
        task.setName("11");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Task>> violations = validator.validate(task);

        assertEquals(1, violations.size());
    }
}
