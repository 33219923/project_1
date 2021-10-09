package za.ac.nwu.as.domain.services;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeneralResponse<T> {
    @JsonProperty(value = "success", index = 1)
    public boolean Success = true;

    @JsonProperty(value = "data", index = 2)
    public T Data;

    @JsonProperty(value = "errorMessage", index = 3)
    public String ErrorMessage;
}
