package za.ac.nwu.as.domain.persistence;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="ACCOUNT" )
public class Account implements Serializable {

    private UUID id;
    private Currency currency;
    private double balance;
    private LocalDate startDate;
    private LocalDate createdDate;
    private Set<Transaction> transactions;

    public Account() {
    }

    public Account(UUID id, Currency currency, double balance, LocalDate startDate, LocalDate createdDate, Set<Transaction> transactions) {
        this.id = id;
        this.currency = currency;
        this.balance = balance;
        this.startDate = startDate;
        this.createdDate = createdDate;
        this.transactions = transactions;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ACCOUNT_ID", updatable = false, nullable = false)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="CURRENCY_ID")
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Column(name = "BALANCE")
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Column(name = "START_DATE")
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Column(name = "CREATED_DATE")
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    @OneToMany(targetEntity = Transaction.class, fetch = FetchType.LAZY, mappedBy = "account", orphanRemoval = true, cascade = CascadeType.REMOVE)
    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(account.balance, balance) == 0 && Objects.equals(id, account.id) && Objects.equals(currency, account.currency) && Objects.equals(transactions, account.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currency, balance, transactions);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", currency=" + currency +
                ", balance=" + balance +
                ", transactions=" + transactions +
                '}';
    }
}