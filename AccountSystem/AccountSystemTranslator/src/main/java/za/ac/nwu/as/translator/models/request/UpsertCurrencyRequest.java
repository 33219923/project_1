package za.ac.nwu.as.translator.models.request;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpsertCurrencyRequest {
    @JsonProperty(value = "id", required = false)
    public UUID Id = UUID.randomUUID();

    @JsonProperty("name")
    public String Name;

    @JsonProperty("description")
    public String Description;

    @JsonProperty("currencySymbol")
    public String CurrencySymbol;
}
