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
    private static final String nameValidationMessage = "Name field is required";
    private static final String budgetNotNullValidationMessage = "Budget may not be null";
    private static final String budgetNotNegativeValidationMessage = "Budget cannot be negative";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private long id;

    @NotBlank(message = nameValidationMessage)
    @Column(name = "name")
    @Setter
    @Getter
    private String name;

    @Column(name = "description")
    @Setter
    @Getter
    private String description;

    @NotNull(message = budgetNotNullValidationMessage)
    @Min(value=0, message = budgetNotNegativeValidationMessage)
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
