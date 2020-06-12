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
    private static final String emailNotNullValidationMessage = "Email may not be null";
    private static final String emailValidationMessage = "Email may not be null";
    private static final String passwordNotNullValidationMessage = "Password may not be null";
    private static final String passwordRequiredValidationMessage = "Password field is required";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @NotNull(message = emailNotNullValidationMessage)
    @Email(message = emailValidationMessage)
    @Getter
    @Setter
    private String email;

    @NotNull(message = passwordNotNullValidationMessage)
    @NotBlank(message = passwordRequiredValidationMessage)
    @Getter
    @Setter
    private String password;

    public User(){}

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }
}