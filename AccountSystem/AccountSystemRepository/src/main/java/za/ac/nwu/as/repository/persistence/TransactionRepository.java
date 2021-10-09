package za.ac.nwu.as.repository.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.as.domain.dto.TransactionSummaryDto;
import za.ac.nwu.as.domain.persistence.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value =
            "SELECT new za.ac.nwu.as.domain.dto.TransactionSummaryDto(tr.memberId, " +
                    "SUM(CASE WHEN tr.type = 'SUBTRACT' THEN - tr.amount ELSE tr.amount END), " +
                    "tr.currency.symbol) " +
                    "FROM Transaction tr " +
                    "WHERE tr.memberId = :memberId " +
                    "AND tr.currency.id = :currencyId " +
                    "GROUP BY tr.memberId, tr.currency.id, tr.currency.symbol")
    List<TransactionSummaryDto> getSummariesForCurrency(Long memberId, Long currencyId);

    @Query(value =
            "SELECT new za.ac.nwu.as.domain.dto.TransactionSummaryDto(tr.memberId, " +
                    "SUM(CASE WHEN tr.type = 'SUBTRACT' THEN - tr.amount ELSE tr.amount END), " +
                    "tr.currency.symbol) " +
                    "FROM Transaction tr " +
                    "WHERE tr.memberId = :memberId " +
                    "GROUP BY tr.memberId, tr.currency.id, tr.currency.symbol")
    List<TransactionSummaryDto> getSummaries(Long memberId);
}
