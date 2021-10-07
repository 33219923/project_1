package za.ac.nwu.as.translator.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.UUID;

public class IncreaseAccountBalanceRequest {
    @JsonProperty("accountId")
    public Long AccountId;

    @JsonProperty("amount")
    public Double Amount;

    @JsonProperty(value = "startDate", required = false)
    public Date StartDate;
}
