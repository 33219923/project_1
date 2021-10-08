package za.ac.nwu.as.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import za.ac.nwu.as.domain.persistence.Currency;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class CurrencyDto implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String symbol;
    private LocalDate createdDate;

    public CurrencyDto() {
    }

    public CurrencyDto(Currency currency) {
        this.id = currency.getId();
        this.name = currency.getName();
        this.description = currency.getDescription();
        this.symbol = currency.getSymbol();
        this.createdDate = currency.getCreatedDate();
    }

    public CurrencyDto(Long id, String name, String description, String symbol) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.symbol = symbol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    @JsonIgnore
    public Currency getCurrency(){
        return new Currency(this.getId(), this.getName(), this.getDescription(), this.getSymbol(), this.getCreatedDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyDto that = (CurrencyDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(symbol, that.symbol) && Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, symbol, createdDate);
    }

    @Override
    public String toString() {
        return "CurrencyDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", symbol='" + symbol + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
