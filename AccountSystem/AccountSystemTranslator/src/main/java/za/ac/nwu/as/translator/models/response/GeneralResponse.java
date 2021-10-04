package za.ac.nwu.as.translator.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import za.ac.nwu.as.translator.interfaces.IResponseData;

public class GeneralResponse {
    @JsonProperty("success")
    public boolean Success = true;

    @JsonProperty("errorMessage")
    public String ErrorMessage;

    @JsonProperty("data")
    public IResponseData Data;
}
