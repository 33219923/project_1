package za.ac.nwu.as.domain.persistence;

import za.ac.nwu.as.domain.enums.TransactionType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Transaction implements Serializable {
    private UUID id;
    private Account account;
    private TransactionType type;
    private double value;
    private LocalDate createdDate;

    public Transaction() {
    }

    public Transaction(UUID id, TransactionType type, double value, LocalDate createdDate) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.createdDate = createdDate;
    }

    public UUID getId() {
        return id;
    }

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ACCOUNT_ID")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    @Column(name = "VALUE")
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Column(name = "CREATED_DATE")
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.value, value) == 0 && Objects.equals(id, that.id) && Objects.equals(account, that.account) && type == that.type && Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, type, value, createdDate);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", account=" + account +
                ", type=" + type +
                ", value=" + value +
                ", createdDate=" + createdDate +
                '}';
    }
}
