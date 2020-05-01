package com.financetracker.server.data.entity;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "records")
public class Record implements Serializable {

    private static final long serialVersionUID = -2343243243242732341L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Id
    @NotBlank(message = "Uuid field is required")
    @Column(name = "uid")
    private String uid;

    //TODO add enum validation
    @NotNull(message = "record_type may not be null")
    @Column(name = "record_type")
    private int recordType;

    @Column(name = "description")
    private String description;

    @NotNull(message = "Amount may not be null")
    @Column(name = "amount")
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "uid")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "uid")
    private Category category;

    public int getRecordType() {
        return recordType;
    }

    public void setRecordType(int recordType) {
        this.recordType = recordType;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
