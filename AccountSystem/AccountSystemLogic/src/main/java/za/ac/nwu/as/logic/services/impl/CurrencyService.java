package za.ac.nwu.as.logic.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.CurrencyDto;
import za.ac.nwu.as.logic.services.ICurrencyService;
import za.ac.nwu.as.translator.services.ICurrencyTranslator;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Component
public class CurrencyService implements ICurrencyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyService.class);

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
