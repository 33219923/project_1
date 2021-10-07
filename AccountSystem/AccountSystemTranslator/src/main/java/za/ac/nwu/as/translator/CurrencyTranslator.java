package za.ac.nwu.as.translator;

import za.ac.nwu.as.domain.dto.CurrencyDto;
import za.ac.nwu.as.domain.persistence.Currency;
import za.ac.nwu.as.repository.persistence.CurrencyRepository;
import za.ac.nwu.as.translator.interfaces.ICurrencyTranslator;

import java.util.ArrayList;
import java.util.List;

public class CurrencyTranslator implements ICurrencyTranslator {

    private final CurrencyRepository currencyRepository;

    public CurrencyTranslator(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<CurrencyDto> getAllCurrencies(){
        List<CurrencyDto> list = new ArrayList<>();
        try {
            for(Currency tempCurrency: currencyRepository.findAll()) {
                list.add(new CurrencyDto(tempCurrency));
            }
        } catch (Exception e) {
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
            throw new RuntimeException("Failed to upsert the currency in the database.", e);
        }
    }
}
