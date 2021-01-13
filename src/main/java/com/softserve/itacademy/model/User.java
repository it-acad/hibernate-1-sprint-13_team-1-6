package com.softserve.itacademy.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;


@Entity
@Table(name = "users")
public class User  {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "user_sequence"),
                    @Parameter(name = "initial_value", value = "10"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long id;

    @Email
    @NotBlank(message = "Email cannot be empty")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Pattern(
            regexp = "[A-Z][a-z]+-([A-Z][a-z]+)",
            message = "Invalid First Name"
    )
    @NotBlank(message = "First Name cannot be empty")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Pattern(
            regexp = "[A-Z][a-z]+-([A-Z][a-z]+)",
            message = "Invalid Last Name"
    )
    @NotBlank(message = "First Name cannot be empty")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Pattern(
            regexp= "^(?=.*?[A-Z])(?=(.*[a-z])+)(?=(.*[\\d])+)(?=(.*[\\W])+)(?!.*\\s).{8,}$",
            message ="Password must contain: at least one number, at least one upper case letter, at least one lower case letter, at least one special character"
    )
    @NotBlank(message = "Password cannot be empty")
    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "owner")
    private List<ToDo> todos;

    @ManyToMany
    @JoinTable(name = "todo_collaborator",
            joinColumns = {@JoinColumn(name = "collaborator_id", referencedColumnName = "id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "todo_id", referencedColumnName = "id", nullable = false)})
    private List<ToDo> collaborations;

    public long getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }
}
