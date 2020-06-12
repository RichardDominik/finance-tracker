package com.financetracker.server.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 6620827911165531787L;
    private static final String EMAIL_NOT_NULL_VALIDATION_MESSAGE = "Email may not be null";
    private static final String EMAIL_VALIDATION_MESSAGE = "Email may not be null";
    private static final String PASSWORD_NOT_NULL_VALIDATION_MESSAGE = "Password may not be null";
    private static final String PASSWORD_REQUIRED_VALIDATION_MESSAGE = "Password field is required";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @NotNull(message = EMAIL_NOT_NULL_VALIDATION_MESSAGE)
    @Email(message = EMAIL_VALIDATION_MESSAGE)
    @Getter
    @Setter
    private String email;

    @NotNull(message = PASSWORD_NOT_NULL_VALIDATION_MESSAGE)
    @NotBlank(message = PASSWORD_REQUIRED_VALIDATION_MESSAGE)
    @Getter
    @Setter
    private String password;

    public User(){}

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }
}