package za.ac.nwu.as.logic.services.interfaces;

import za.ac.nwu.as.domain.dto.CurrencyDto;
import za.ac.nwu.as.translator.models.request.UpsertCurrencyRequest;

public interface ICurrencyService {

    CurrencyDto UpsertCurrency(UpsertCurrencyRequest currencyRequest);
}
