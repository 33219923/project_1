package za.ac.nwu.as.logic.services.interfaces;

import za.ac.nwu.as.domain.dto.AccountDto;
import za.ac.nwu.as.translator.models.request.DecreaseAccountBalanceRequest;
import za.ac.nwu.as.translator.models.request.IncreaseAccountBalanceRequest;

public interface IAccountService {
    AccountDto increaseBalance(IncreaseAccountBalanceRequest increaseRequest);

    AccountDto decreaseBalance(DecreaseAccountBalanceRequest decreaseRequest);

    AccountDto viewBalance(String accountId);
}
