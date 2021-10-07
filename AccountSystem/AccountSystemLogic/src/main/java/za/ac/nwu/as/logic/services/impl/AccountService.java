package za.ac.nwu.as.logic.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.AccountDto;
import za.ac.nwu.as.logic.services.IAccountService;
import za.ac.nwu.as.translator.models.request.DecreaseAccountBalanceRequest;
import za.ac.nwu.as.translator.models.request.IncreaseAccountBalanceRequest;
import za.ac.nwu.as.translator.services.IAccountTranslator;

import javax.transaction.Transactional;

@Transactional
@Component
public class AccountService implements IAccountService {

    private final IAccountTranslator accountTranslator;

    @Autowired
    public AccountService(IAccountTranslator accountTranslator) {
        this.accountTranslator = accountTranslator;
    }

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
