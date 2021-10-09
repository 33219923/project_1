package za.ac.nwu.as.logic.services;

import za.ac.nwu.as.domain.dto.TransactionDto;
import za.ac.nwu.as.domain.dto.TransactionSummaryDto;

public interface ITransactionService {
    TransactionDto add(TransactionDto transaction);
    TransactionDto subtract(TransactionDto transaction);
    TransactionSummaryDto getBalance(Long memberId);
}
