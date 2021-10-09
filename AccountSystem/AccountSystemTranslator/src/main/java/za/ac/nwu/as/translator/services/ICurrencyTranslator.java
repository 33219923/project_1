package za.ac.nwu.as.translator.services;

import za.ac.nwu.as.domain.dto.CurrencyDto;
import za.ac.nwu.as.domain.exceptions.CustomException;

import java.util.List;

public interface ICurrencyTranslator {
    CurrencyDto getCurrencyById(Long currencyId) throws CustomException;
    List<CurrencyDto> getAllCurrencies();
    CurrencyDto upsert (CurrencyDto currencyDto);
}
