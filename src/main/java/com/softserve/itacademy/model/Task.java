package com.softserve.itacademy.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "tasks_sequence"),
                    @Parameter(name = "initial_value", value = "10"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    int id;

    @NotBlank(message = "Task Name cannot be empty")
    @Size(min = 3, max = 200)
    @Column(name = "name", nullable = false, unique = true)
    private String name;


    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false, unique = true,  columnDefinition = "VARCHAR(255)")
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    private State state;

    @ManyToOne
    @JoinColumn(name = "todo_id", referencedColumnName = "id")
    private  ToDo todo;

    public void setName(String name) {
        this.name = name;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setTodo(ToDo todo) {
        this.todo = todo;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Priority getPriority() {
        return priority;
    }

    public State getState() {
        return state;
    }

    public ToDo getTodo() {
        return todo;
    }
}
