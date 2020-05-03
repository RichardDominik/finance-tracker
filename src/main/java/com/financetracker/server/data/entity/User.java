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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @NotNull(message = "Email may not be null")
    @Email(message = "Email field must be valid email address")
    @Getter
    @Setter
    private String email;

    @NotNull(message = "Password may not be null")
    @NotBlank(message = "Password field is required")
    @Getter
    @Setter
    private String password;

    public User(){}

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }
}