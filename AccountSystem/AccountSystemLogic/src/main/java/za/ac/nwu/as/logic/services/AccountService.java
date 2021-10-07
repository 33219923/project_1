package za.ac.nwu.as.logic.services;

import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.AccountDto;
import za.ac.nwu.as.logic.services.interfaces.IAccountService;
import za.ac.nwu.as.translator.models.request.DecreaseAccountBalanceRequest;
import za.ac.nwu.as.translator.models.request.IncreaseAccountBalanceRequest;

import javax.transaction.Transactional;

@Transactional
@Component
public class AccountService implements IAccountService {

    public AccountDto increaseBalance(IncreaseAccountBalanceRequest increaseRequest) {
        return new AccountDto();
    }

    public AccountDto decreaseBalance(DecreaseAccountBalanceRequest decreaseRequest) {
        return new AccountDto();
    }

    public AccountDto viewBalance(String accountId) {

        return new AccountDto();
    }
}
