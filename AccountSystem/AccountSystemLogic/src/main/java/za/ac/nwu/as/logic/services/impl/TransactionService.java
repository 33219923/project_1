package za.ac.nwu.as.logic.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.TransactionDto;
import za.ac.nwu.as.domain.dto.TransactionSummaryDto;
import za.ac.nwu.as.logic.services.ITransactionService;
import za.ac.nwu.as.translator.services.ITransactionTranslator;

import javax.transaction.Transactional;

@Transactional
@Component
public class TransactionService implements ITransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    private final ITransactionTranslator transactionTranslator;

    @Autowired
    public TransactionService(ITransactionTranslator transactionTranslator) {
        this.transactionTranslator = transactionTranslator;
    }

    @Override
    public TransactionDto add(TransactionDto transaction) {
        return this.transactionTranslator.createTransaction(transaction);
    }

    @Override
    public TransactionDto subtract(TransactionDto transaction) {
        return this.transactionTranslator.createTransaction(transaction);
    }

    @Override
    public TransactionSummaryDto getBalance(Long memberId) {
        return this.transactionTranslator.getTransactionSummaryByMemberId(memberId);
    }
}
