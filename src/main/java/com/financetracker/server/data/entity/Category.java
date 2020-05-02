package com.financetracker.server.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "categories")
public class Category implements Serializable {

    private static final long serialVersionUID = -2343243243242432323L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private long id;

    @NotBlank(message = "Name field is required")
    @Column(name = "name")
    @Setter
    @Getter
    private String name;

    @Column(name = "description")
    @Setter
    @Getter
    private String description;

    @NotNull(message = "Budget may not be null")
    @Min(value=0, message="Budget cannot be negative")
    @Column(name = "budget")
    @Setter
    @Getter
    private BigDecimal budget;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Setter
    @Getter
    private User user;
}
