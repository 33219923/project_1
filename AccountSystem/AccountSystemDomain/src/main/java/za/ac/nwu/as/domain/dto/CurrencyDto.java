package za.ac.nwu.as.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.as.domain.persistence.Currency;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class CurrencyDto implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String symbol;

    private LocalDateTime createdDate;

    public CurrencyDto() {
        this.createdDate = LocalDateTime.now();
    }

    public CurrencyDto(Long id, String name, String description, String symbol) {
        this();
        this.id = id;
        this.name = name;
        this.description = description;
        this.symbol = symbol;
    }

    public CurrencyDto(Currency currency) {
        this.id = currency.getId();
        this.name = currency.getName();
        this.description = currency.getDescription();
        this.symbol = currency.getSymbol();
        this.createdDate = currency.getCreatedDate();
    }

    @ApiModelProperty(
            name = "currencyId",
            example = "0",
            notes = "The currency unique identifier.",
            dataType = "java.lang.Long",
            position = 1
    )
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ApiModelProperty(
            name = "name",
            example = "MILES",
            notes = "The currency name.",
            dataType = "java.lang.String",
            position = 2,
            required = true
    )
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(
            name = "description",
            example = "A currency made of MILES",
            notes = "The currency description.",
            dataType = "java.lang.String",
            position = 3,
            required = true
    )
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ApiModelProperty(
            name = "symbol",
            example = "M",
            notes = "The currency symbol.",
            dataType = "java.lang.String",
            position = 4,
            required = true
    )
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @ApiModelProperty(
            name = "createdDate",
            notes = "The created date and time of the currency.",
            dataType = "java.lang.LocalDateTime",
            position = 4,
            accessMode = ApiModelProperty.AccessMode.READ_ONLY,
            allowEmptyValue = true
    )
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @JsonIgnore
    public Currency getCurrency() {
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
