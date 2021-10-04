package za.ac.nwu.as.translator.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class DecreaseAccountBalanceRequest {
    @JsonProperty("accountId")
    public UUID AccountId;

    @JsonProperty("amount")
    public double Amount;
}
