package za.ac.nwu.as.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import za.ac.nwu.as.domain.persistence.Currency;

import java.io.Serializable;

public class CurrencyDto implements Serializable {

    public CurrencyDto() {
    }

    public CurrencyDto(Currency currency) {
    }

    @JsonIgnore
    public Currency getCurrency(){
        return new Currency();
    }
}
