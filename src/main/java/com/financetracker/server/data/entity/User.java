package com.financetracker.server.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

    public User(@Email @Size(max = 40) String email, @NotBlank @Size(max = 100) @NotNull String password,
                @NotNull LoginMethodEnum loginMethodEnum) {
        this.email = email;
        this.password = password;
        this.loginMethodEnum = loginMethodEnum;
        this.userRole = userRole;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(name = "email")
    @Size(max = 40)
    private String email;

    @NotBlank
    @Column(name = "password")
    @Size(max = 100)
    @NotNull
    private String password;

    @NotNull
    @Column(name = "login_method_enum")
    @Enumerated(EnumType.STRING)
    private LoginMethodEnum loginMethodEnum;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginMethodEnum getLoginMethodEnum() {
        return loginMethodEnum;
    }

    public void setLoginMethodEnum(LoginMethodEnum loginMethodEnum) {
        this.loginMethodEnum = loginMethodEnum;
    }
}
