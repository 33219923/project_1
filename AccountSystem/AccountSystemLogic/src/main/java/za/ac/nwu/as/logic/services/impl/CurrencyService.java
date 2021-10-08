package za.ac.nwu.as.logic.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import za.ac.nwu.as.domain.dto.CurrencyDto;
import za.ac.nwu.as.logic.services.ICurrencyService;
import za.ac.nwu.as.translator.services.ICurrencyTranslator;
import za.ac.nwu.as.translator.models.request.UpsertCurrencyRequest;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Component
public class CurrencyService implements ICurrencyService {

    private final ICurrencyTranslator currencyTranslator;

    @Autowired
    public CurrencyService(ICurrencyTranslator currencyTranslator) {
        this.currencyTranslator = currencyTranslator;
    }

    @Override
    public List<CurrencyDto> listAll() {
        return currencyTranslator.getAllCurrencies();
    }

    @Override
    public CurrencyDto upsertCurrency(CurrencyDto currency) {
        return this.currencyTranslator.upsert(currency);
    }
}
