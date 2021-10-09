package za.ac.nwu.as.domain.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "CURRENCIES")
public class Currency implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String symbol;
    private LocalDate createdDate;
    private Set<Transaction> transactions;

    public Currency() {
    }
    public Currency(Long id, String name, String description, String symbol, LocalDate createdDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.symbol = symbol;
        this.createdDate = createdDate;
    }

    public Currency(Long id, String name, String description, String symbol, LocalDate createdDate, Set<Transaction> transactions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.symbol = symbol;
        this.createdDate = createdDate;
        this.transactions = transactions;
    }

    @Id
    @SequenceGenerator(name= "CURRENCY_GENERIC_SEQ", sequenceName = "CURRENCY_GENERIC_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CURRENCY_GENERIC_SEQ" )
    @Column(name = "CURRENCY_ID", updatable = false, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "SYMBOL")
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Column(name = "CREATED_DATE")
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    @OneToMany(targetEntity = Transaction.class, fetch = FetchType.LAZY, mappedBy = "currency", orphanRemoval = true, cascade = CascadeType.PERSIST)
    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setAccounts(Set<Transaction> accounts) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency that = (Currency) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(symbol, that.symbol) && Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, symbol, createdDate);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", symbol='" + symbol + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
