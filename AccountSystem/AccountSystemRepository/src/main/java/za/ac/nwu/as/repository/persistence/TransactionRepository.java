package za.ac.nwu.as.repository.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.as.domain.dto.TransactionSummaryDto;
import za.ac.nwu.as.domain.persistence.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value =
            "SELECT new za.ac.nwu.as.domain.dto.TransactionSummaryDto(tr.memberId, SUM(tr.amount))" +
                    "FROM TRANSACTIONS tr" +
                    "WHERE tr.memberId = :memberId")
    TransactionSummaryDto getBalanceByMemberId(Long memberId);
}
