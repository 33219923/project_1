package za.ac.nwu.as.repository.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.nwu.as.domain.persistence.Transaction;

import java.util.UUID;

@Repository
public interface CurrencyRepository extends JpaRepository<Transaction, UUID> {
}