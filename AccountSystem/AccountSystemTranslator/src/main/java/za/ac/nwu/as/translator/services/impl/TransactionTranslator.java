package za.ac.nwu.as.translator.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.TransactionDto;
import za.ac.nwu.as.domain.dto.TransactionSummaryDto;
import za.ac.nwu.as.domain.persistence.Transaction;
import za.ac.nwu.as.repository.persistence.TransactionRepository;
import za.ac.nwu.as.translator.services.ITransactionTranslator;

import javax.transaction.Transactional;

@Transactional
@Component
public class TransactionTranslator implements ITransactionTranslator {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionTranslator.class);
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionTranslator(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        try {
            Transaction temp = transactionRepository.save(transactionDto.getTransation());
            return new TransactionDto(temp);
        } catch (Exception e) {
            LOGGER.error("Database error: {}", e);
            throw new RuntimeException("Failed to save the transaction to the database.", e);
        }
    }

    @Override
    public TransactionSummaryDto getTransactionSummaryByMemberId(Long memberId) {
        try {
            return transactionRepository.getBalanceByMemberId(memberId);
        } catch (Exception e) {
            LOGGER.error("Database error: {}", e);
            throw new RuntimeException("Failed to retrieve the transaction summary from the database.", e);
        }
    }
}
