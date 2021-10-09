package za.ac.nwu.as.logic.services;

import za.ac.nwu.as.domain.dto.TransactionDto;
import za.ac.nwu.as.domain.dto.TransactionSummaryDto;
import za.ac.nwu.as.domain.exceptions.CustomException;

import java.util.List;

public interface ITransactionService {
    TransactionDto add(TransactionDto transaction) throws CustomException;
    TransactionDto subtract(TransactionDto transaction) throws CustomException;
    List<TransactionSummaryDto> getTransactionSummaries(Long memberId, Long currencyId);
}
