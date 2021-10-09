package za.ac.nwu.as.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.as.domain.enums.TransactionType;
import za.ac.nwu.as.domain.persistence.Transaction;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class TransactionDto implements Serializable {
    private Long id;
    private Long memberId;
    private Long currencyId;
    private Double amount;
    private TransactionType type;
    private LocalDateTime createdDate;

    public TransactionDto() {
        this.createdDate = LocalDateTime.now();
    }

    public TransactionDto(Long id, Long memberId,Long currencyId, Double amount, TransactionType type, LocalDateTime createdDate) {
        this();
        this.id = id;
        this.memberId = memberId;
        this.currencyId = currencyId;
        this.amount = amount;
        this.type = type;
        this.createdDate = createdDate;
    }

    public TransactionDto(Transaction transaction) {
        this.id = transaction.getId();
        this.memberId = transaction.getMemberId();
        this.amount = transaction.getAmount();
        this.type = transaction.getType();
        this.createdDate = transaction.getCreatedDate();
        this.currencyId = transaction.getCurrency().getId();
    }

    @ApiModelProperty(
            name = "transactionId",
            example = "0",
            notes = "The transaction unique identifier.",
            dataType = "java.lang.Long",
            position = 1,
            accessMode = ApiModelProperty.AccessMode.READ_ONLY
    )
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ApiModelProperty(
            name = "memberId",
            example = "1",
            notes = "The unique identifier of the member.",
            dataType = "java.lang.Long",
            position = 2,
            required = true
    )
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @ApiModelProperty(
            name = "memberId",
            example = "1",
            notes = "The unique identifier of the currency.",
            dataType = "java.lang.Long",
            position = 3,
            required = true
    )
    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    @ApiModelProperty(
            name = "amount",
            example = "0.0",
            notes = "The transaction amount.",
            dataType = "java.lang.Double",
            position = 4,
            required = true
    )
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @ApiModelProperty(
            name = "transactionType",
            example = "ADD",
            notes = "The transaction amount.",
            dataType = "za.ac.nwu.as.domain.enums.TransactionType",
            position = 5,
            accessMode = ApiModelProperty.AccessMode.READ_ONLY,
            required = false
    )
    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    @ApiModelProperty(
            name = "createdDate",
            notes = "The created date and time of the transaction.",
            dataType = "java.lang.LocalDateTime",
            position = 6,
            allowEmptyValue = true
    )
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @JsonIgnore
    public Transaction getTransaction() {
        return new Transaction(this.getMemberId(), this.getType(), this.getAmount(), this.getCreatedDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDto that = (TransactionDto) o;
        return Objects.equals(id, that.id) && Objects.equals(memberId, that.memberId) && Objects.equals(amount, that.amount) && type == that.type && Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, memberId, amount, type, createdDate);
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", amount=" + amount +
                ", type=" + type +
                ", createdDate=" + createdDate +
                '}';
    }
}
