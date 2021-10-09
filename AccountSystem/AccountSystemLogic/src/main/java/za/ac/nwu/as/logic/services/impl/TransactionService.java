package za.ac.nwu.as.logic.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.TransactionDto;
import za.ac.nwu.as.domain.dto.TransactionSummaryDto;
import za.ac.nwu.as.domain.enums.TransactionType;
import za.ac.nwu.as.domain.exceptions.CustomException;
import za.ac.nwu.as.logic.services.ITransactionService;
import za.ac.nwu.as.translator.services.ICurrencyTranslator;
import za.ac.nwu.as.translator.services.ITransactionTranslator;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Component
public class TransactionService implements ITransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    private final ITransactionTranslator transactionTranslator;
    private final ICurrencyTranslator currencyTranslator;

    @Autowired
    public TransactionService(ITransactionTranslator transactionTranslator, ICurrencyTranslator currencyTranslator) {
        this.transactionTranslator = transactionTranslator;
        this.currencyTranslator = currencyTranslator;

        LOGGER.debug("Service instantiated: " + this.getClass().getSimpleName() + " Time: " + System.nanoTime());
    }

    @Override
    public TransactionDto add(TransactionDto transaction) throws CustomException {
        LOGGER.debug("Service Called: " + this.getClass().getSimpleName() + " Method " + this.getClass().getEnclosingMethod() + " Time: " + System.nanoTime());

        //Validate the request
        if (0 == transaction.getMemberId()) {
            LOGGER.debug("Member id is zero. Throwing custom exception with bad request.");
            throw new CustomException("The member id cannot be null or zero.", HttpStatus.BAD_REQUEST);
        }
        if (0 == transaction.getCurrencyId()) {
            LOGGER.debug("Currency id is zero. Throwing custom exception with bad request.");
            throw new CustomException("The currency id cannot be null or zero.", HttpStatus.BAD_REQUEST);
        }

        transaction.setType(TransactionType.ADD);
        var currency = this.currencyTranslator.getCurrencyById(transaction.getCurrencyId());

        return this.transactionTranslator.createTransaction(transaction, currency);
    }

    @Override
    public TransactionDto subtract(TransactionDto transaction) throws CustomException {
        LOGGER.debug("Service Called: " + this.getClass().getSimpleName() + " Method " + this.getClass().getEnclosingMethod() + " Time: " + System.nanoTime());

        //Validate the request
        if (0 == transaction.getMemberId()) {
            LOGGER.debug("Member id is zero. Throwing custom exception with bad request.");
            throw new CustomException("The member id cannot be null or zero.", HttpStatus.BAD_REQUEST);
        }
        if (0 == transaction.getCurrencyId()) {
            LOGGER.debug("Currency id is zero. Throwing custom exception with bad request.");
            throw new CustomException("The currency id cannot be null or zero.", HttpStatus.BAD_REQUEST);
        }

        transaction.setCreatedDate(LocalDateTime.now());
        transaction.setType(TransactionType.SUBTRACT);
        var currency = this.currencyTranslator.getCurrencyById(transaction.getCurrencyId());

        return this.transactionTranslator.createTransaction(transaction, currency);
    }

    @Override
    public List<TransactionSummaryDto> getTransactionSummaries(Long memberId, Long currencyId) {
        LOGGER.debug("Service Called: " + this.getClass().getSimpleName() + " Method " + this.getClass().getEnclosingMethod() + " Time: " + System.nanoTime());

        return this.transactionTranslator.getTransactionSummaries(memberId, currencyId);
    }
}
