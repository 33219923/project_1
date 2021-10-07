package za.ac.nwu.as.logic.services;

import za.ac.nwu.as.logic.services.interfaces.IAccountService;
import za.ac.nwu.as.translator.models.request.DecreaseAccountBalanceRequest;
import za.ac.nwu.as.translator.models.request.IncreaseAccountBalanceRequest;
import za.ac.nwu.as.translator.models.response.UserAccountDto;

public class AccountService implements IAccountService {

    public static UserAccountDto IncreaseBalance(IncreaseAccountBalanceRequest increaseRequest) {
        return new UserAccountDto();
    }

    public static UserAccountDto DecreaseBalance(DecreaseAccountBalanceRequest decreaseRequest) {
        return new UserAccountDto();
    }

    public static UserAccountDto ViewBalance(String accountId) {

        return new UserAccountDto();
    }
}
