package com.softserve.itacademy.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StateTest {
    @Test
    void createValidState() {
        State state = new State();
        state.setName("_Valid Name - 1_");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<State>> violations = validator.validate(state);

        assertEquals(0, violations.size());
    }

    @Test
    void stateNameLengthConstraintViolation() {
        State state = new State();
        state.setName("Long state name 1234567890 1234567890 1234567890");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<State>> violations = validator.validate(state);

        assertEquals(1, violations.size());
    }

    @Test
    void stateNamePatternConstraintViolation() {
        State state = new State();
        state.setName("forbidden char - #");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<State>> violations = validator.validate(state);

        assertEquals(1, violations.size());
    }

    @Test
    void stateNamePatternAndLengthConstraintViolation() {
        State state = new State();
        state.setName("forbidden char - # and too long state name .............");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<State>> violations = validator.validate(state);

        assertEquals(2, violations.size());
    }
}
