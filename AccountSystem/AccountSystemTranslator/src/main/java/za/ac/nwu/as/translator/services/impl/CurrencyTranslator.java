package za.ac.nwu.as.translator.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.CurrencyDto;
import za.ac.nwu.as.domain.persistence.Currency;
import za.ac.nwu.as.repository.persistence.CurrencyRepository;
import za.ac.nwu.as.translator.services.ICurrencyTranslator;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class CurrencyTranslator implements ICurrencyTranslator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyTranslator.class);
    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyTranslator(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<CurrencyDto> getAllCurrencies() {
        List<CurrencyDto> list = new ArrayList<>();
        try {
            for (Currency tempCurrency : currencyRepository.findAll()) {
                list.add(new CurrencyDto(tempCurrency));
            }
        } catch (Exception e) {
            LOGGER.error("Database error: {}", e);
            throw new RuntimeException("Failed to retrieve currency list from the database.", e);
        }
        return list;
    }

    @Override
    public CurrencyDto upsert(CurrencyDto currencyDto) {
        try {
            Currency tempCurrency = currencyRepository.save(currencyDto.getCurrency());
            return new CurrencyDto(tempCurrency);
        } catch (Exception e) {
            LOGGER.error("Database error: {}", e);
            throw new RuntimeException("Failed to upsert the currency in the database.", e);
        }
    }
}
