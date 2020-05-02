package com.financetracker.server.data.entity;

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "type may not be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "record_type")
    @Type( type = "pgsql_enum" )
    private RecordType type;

    @Column(name = "description")
    private String description;

    @NotNull(message = "Amount may not be null")
    @Column(name = "amount")
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    public RecordType getRecordType() {
        return type;
    }

    public void setType(RecordType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
