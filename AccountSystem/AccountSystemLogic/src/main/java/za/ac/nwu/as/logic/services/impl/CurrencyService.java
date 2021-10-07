package za.ac.nwu.as.logic.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import za.ac.nwu.as.domain.dto.CurrencyDto;
import za.ac.nwu.as.logic.services.ICurrencyService;
import za.ac.nwu.as.translator.services.ICurrencyTranslator;
import za.ac.nwu.as.translator.models.request.UpsertCurrencyRequest;

import javax.transaction.Transactional;

@Transactional
@Component
public class CurrencyService implements ICurrencyService {

    private final ICurrencyTranslator currencyTranslator;

    @Autowired
    public CurrencyService(ICurrencyTranslator currencyTranslator) {
        this.currencyTranslator = currencyTranslator;
    }

    @Override
    public CurrencyDto UpsertCurrency(UpsertCurrencyRequest currencyRequest) {
        //Handle upsert logic - When id is empty or null add a new currency else update existing currency
        if (StringUtils.isEmpty(currencyRequest.Id)) {
            //Add new currency
            this.currencyTranslator.upsert(new CurrencyDto());
        } else {
            //Update existing currency
            this.currencyTranslator.upsert(new CurrencyDto());
        }

        return new CurrencyDto();
    }
}
