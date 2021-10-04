package za.ac.nwu.as.logic.models.request;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpsertCurrencyRequest {
    @JsonProperty("id")
    public UUID Id = UUID.randomUUID();

    @JsonProperty("name")
    public String Name;

    @JsonProperty("description")
    public String Description;

    @JsonProperty("currencySymbol")
    public String CurrencySymbol;
}
