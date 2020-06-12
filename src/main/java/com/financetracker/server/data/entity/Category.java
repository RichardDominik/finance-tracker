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
    private static final String NAME_VALIDATION_MESSAGE = "Name field is required";
    private static final String BUDGET_NOT_NULL_VALIDATION_MESSAGE = "Budget may not be null";
    private static final String BUDGET_NOT_NEGATIVE_VALIDATION_MESSAGE = "Budget cannot be negative";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private long id;

    @NotBlank(message = NAME_VALIDATION_MESSAGE)
    @Column(name = "name")
    @Setter
    @Getter
    private String name;

    @Column(name = "description")
    @Setter
    @Getter
    private String description;

    @NotNull(message = BUDGET_NOT_NULL_VALIDATION_MESSAGE)
    @Min(value=0, message = BUDGET_NOT_NEGATIVE_VALIDATION_MESSAGE)
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
