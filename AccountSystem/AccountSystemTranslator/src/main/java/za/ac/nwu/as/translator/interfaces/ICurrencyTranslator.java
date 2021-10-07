package za.ac.nwu.as.translator.interfaces;

import za.ac.nwu.as.domain.dto.CurrencyDto;
import za.ac.nwu.as.translator.models.request.UpsertCurrencyRequest;

import java.util.List;

public interface ICurrencyTranslator {
    List<CurrencyDto> getAllCurrencies();
    CurrencyDto upsert (CurrencyDto currencyDto);
}
