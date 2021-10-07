package za.ac.nwu.as.domain.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeneralResponse<T> {
    @JsonProperty("success")
    public boolean Success = true;

    @JsonProperty("errorMessage")
    public String ErrorMessage;

    @JsonProperty("data")
    public T Data;
}
