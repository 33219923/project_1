package za.ac.nwu.as.translator.services;

import za.ac.nwu.as.domain.dto.TransactionDto;
import za.ac.nwu.as.domain.dto.TransactionSummaryDto;

public interface ITransactionTranslator {
    TransactionDto createTransaction(TransactionDto transaction);
    TransactionSummaryDto getTransactionSummaryByMemberId (Long memberId);
}
