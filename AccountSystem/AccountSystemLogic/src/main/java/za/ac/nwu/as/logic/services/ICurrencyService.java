package za.ac.nwu.as.logic.services;

import za.ac.nwu.as.domain.dto.CurrencyDto;
import java.util.List;

public interface ICurrencyService {
    List<CurrencyDto> listAll();
    CurrencyDto upsertCurrency(CurrencyDto currencyRequest);
}
