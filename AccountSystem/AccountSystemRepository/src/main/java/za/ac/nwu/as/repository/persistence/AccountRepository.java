package za.ac.nwu.as.repository.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.nwu.as.domain.persistence.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}

