package za.ac.nwu.as.logic.services;

import za.ac.nwu.as.domain.dto.CurrencyDto;
import za.ac.nwu.as.translator.models.request.UpsertCurrencyRequest;

import java.util.List;

public interface ICurrencyService {
    List<CurrencyDto> listAll();
    CurrencyDto upsertCurrency(CurrencyDto currencyRequest);
}
