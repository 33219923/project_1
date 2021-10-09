package za.ac.nwu.as.domain.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class TransactionSummaryDto implements Serializable {
    private Long memberId;
    private Double balance;
    private LocalDateTime summaryDate;
    private String currencySymbol;

    public TransactionSummaryDto() {
        this.summaryDate = LocalDateTime.now();
    }

    public TransactionSummaryDto(Long memberId, Double balance, String currencySymbol) {
        this();
        this.memberId = memberId;
        this.balance = balance;
        this.currencySymbol = currencySymbol;
    }

    @ApiModelProperty(
            name = "memberId",
            example = "0",
            notes = "The unique identifier of the member.",
            dataType = "java.lang.Long",
            position = 1
    )
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @ApiModelProperty(
            name = "balance",
            example = "0.0",
            notes = "The unique identifier of the member.",
            dataType = "java.lang.Double",
            position = 2
    )
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @ApiModelProperty(
            name = "currencySymbol",
            example = "$",
            notes = "The currency symbol.",
            dataType = "java.lang.String",
            position = 3
    )
    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    @ApiModelProperty(
            name = "summaryDate",
            notes = "The date and time of the summary report.",
            dataType = "java.lang.LocalDateTime",
            position = 4
    )
    public LocalDateTime getSummaryDate() {
        return summaryDate;
    }

    public void setSummaryDate(LocalDateTime summaryDate) {
        this.summaryDate = summaryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionSummaryDto that = (TransactionSummaryDto) o;
        return Objects.equals(memberId, that.memberId) && Objects.equals(balance, that.balance) && Objects.equals(summaryDate, that.summaryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, balance, summaryDate);
    }

    @Override
    public String toString() {
        return "TransactionSummaryDto{" +
                "memberId=" + memberId +
                ", balance=" + balance +
                ", summaryDate=" + summaryDate +
                '}';
    }
}
