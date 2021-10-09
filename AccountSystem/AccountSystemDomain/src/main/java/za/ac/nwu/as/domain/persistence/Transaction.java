package za.ac.nwu.as.domain.persistence;

import za.ac.nwu.as.domain.enums.TransactionType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="TRANSACTIONS" )
public class Transaction implements Serializable {
    private Long id;
    private Long memberId;
    private TransactionType type;
    private Double amount;
    private LocalDateTime createdDate;

    private Currency currency;

    public Transaction() {

    }

    public Transaction(Long memberId, TransactionType type, Double amount, LocalDateTime createdDate) {
        this.memberId = memberId;
        this.type = type;
        this.amount = amount;
        this.createdDate = createdDate;
    }

    public Transaction(Long id,Long memberId, TransactionType type, Double amount, LocalDateTime createdDate) {
        this.id = id;
        this.memberId = memberId;
        this.type = type;
        this.amount = amount;
        this.createdDate = createdDate;
    }

    @Id
    @SequenceGenerator(name= "TRANSACTION_GENERIC_SEQ", sequenceName = "TRANSACTION_GENERIC_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACTION_GENERIC_SEQ" )
    @Column(name = "TRANSACTION_ID", updatable = false, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "MEMBER_ID")
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="CURRENCY_ID")
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    @Column(name = "amount")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Column(name = "CREATED_DATE")
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(id, that.id) && Objects.equals(memberId, that.memberId) && type == that.type && Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, memberId, type, amount, createdDate, currency);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", type=" + type +
                ", amount=" + amount +
                ", createdDate=" + createdDate +
                ", currency=" + currency +
                '}';
    }
}
