package za.ac.nwu.as.logic;

import org.springframework.util.StringUtils;
import za.ac.nwu.as.translator.models.request.UpsertCurrencyRequest;
import za.ac.nwu.as.translator.models.response.CurrencyDto;
import za.ac.nwu.as.translator.models.response.GeneralResponse;

public class CurrencyLogic {

    public static CurrencyDto UpsertCurrency(UpsertCurrencyRequest currencyRequest){
        //Handle upsert logic - When id is empty or null add a new currency else update existing currency
        if(StringUtils.isEmpty(currencyRequest.Id))
        {
            //Add new currency
        }
        else
        {
            //Update existing currency
        }

        return new CurrencyDto();
    }
}
