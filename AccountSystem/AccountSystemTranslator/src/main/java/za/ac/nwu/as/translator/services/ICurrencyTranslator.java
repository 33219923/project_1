package za.ac.nwu.as.translator.services;

import za.ac.nwu.as.domain.dto.CurrencyDto;

import java.util.List;

public interface ICurrencyTranslator {
    List<CurrencyDto> getAllCurrencies();
    CurrencyDto upsert (CurrencyDto currencyDto);
}
