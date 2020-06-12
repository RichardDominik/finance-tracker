package com.financetracker.server.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "records")
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
public class Record implements Serializable {

    private static final long serialVersionUID = -2343243243242732341L;
    private static final String TYPE_NOT_NULL_VALIDATION_MESSAGE = "Type may not be null";
    private static final String AMOUNT_NOT_NULL_VALIDATION_MESSAGE = "Amount may not be null";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private long id;

    @NotNull(message = TYPE_NOT_NULL_VALIDATION_MESSAGE)
    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "record_type")
    @Type( type = "pgsql_enum" )
    @Getter
    @Setter
    private RecordType type;

    @Column(name = "description")
    @Getter
    @Setter
    private String description;

    @NotNull(message = AMOUNT_NOT_NULL_VALIDATION_MESSAGE)
    @Column(name = "amount")
    @Getter
    @Setter
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Getter
    @Setter
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @Getter
    @Setter
    private Category category;
}
