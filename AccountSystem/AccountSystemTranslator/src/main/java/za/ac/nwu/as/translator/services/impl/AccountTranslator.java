package za.ac.nwu.as.translator.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.repository.persistence.AccountRepository;
import za.ac.nwu.as.translator.services.IAccountTranslator;

import javax.transaction.Transactional;

@Transactional
@Component
public class AccountTranslator implements IAccountTranslator {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountTranslator(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
