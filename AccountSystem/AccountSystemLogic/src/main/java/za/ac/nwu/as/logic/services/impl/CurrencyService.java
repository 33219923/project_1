package za.ac.nwu.as.logic.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.CurrencyDto;
import za.ac.nwu.as.domain.exceptions.CustomException;
import za.ac.nwu.as.logic.services.ICurrencyService;
import za.ac.nwu.as.translator.services.ICurrencyTranslator;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Component
public class CurrencyService implements ICurrencyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyService.class);

    private final ICurrencyTranslator currencyTranslator;

    @Autowired
    public CurrencyService(ICurrencyTranslator currencyTranslator) {
        this.currencyTranslator = currencyTranslator;

        LOGGER.debug("Service instantiated: " + this.getClass().getSimpleName() + " Time: " + System.nanoTime());
    }

    @Override
    public List<CurrencyDto> listAll() {
        LOGGER.debug("Service Called: " + this.getClass().getSimpleName() + " Method " + this.getClass().getEnclosingMethod() + " Time: " + System.nanoTime());

        return this.currencyTranslator.getAllCurrencies();
    }

    @Override
    public CurrencyDto upsertCurrency(CurrencyDto currency) throws CustomException {
        LOGGER.debug("Service Called: " + this.getClass().getSimpleName() + " Method " + this.getClass().getEnclosingMethod() + " Time: " + System.nanoTime());

        if (0 == currency.getId()) {
            currency.setCreatedDate(LocalDateTime.now());
            LOGGER.info("Creating new currency");
            return this.currencyTranslator.upsert(currency);
        } else {
            var existingCurrency = this.currencyTranslator.getCurrencyById(currency.getId());
            existingCurrency.setName(currency.getName());
            existingCurrency.setDescription(currency.getDescription());
            existingCurrency.setSymbol(currency.getSymbol());

            LOGGER.info("Updating existing currency");
            return this.currencyTranslator.upsert(existingCurrency);
        }
    }
}
