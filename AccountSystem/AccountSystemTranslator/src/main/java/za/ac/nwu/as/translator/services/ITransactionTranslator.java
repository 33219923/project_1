package za.ac.nwu.as.translator.services;

import za.ac.nwu.as.domain.dto.CurrencyDto;
import za.ac.nwu.as.domain.dto.TransactionDto;
import za.ac.nwu.as.domain.dto.TransactionSummaryDto;
import za.ac.nwu.as.domain.persistence.Currency;

import java.util.List;

public interface ITransactionTranslator {
    TransactionDto createTransaction(TransactionDto transaction, CurrencyDto currency);

    List<TransactionSummaryDto> getTransactionSummaries(Long memberId, Long currencyId);
}
