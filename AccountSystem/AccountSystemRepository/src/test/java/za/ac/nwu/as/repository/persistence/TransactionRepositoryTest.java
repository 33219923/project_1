package za.ac.nwu.as.repository.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.nwu.as.domain.enums.TransactionType;
import za.ac.nwu.as.domain.persistence.Currency;
import za.ac.nwu.as.domain.persistence.Transaction;
import za.ac.nwu.as.repository.config.RepositoryTestConfig;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {RepositoryTestConfig.class})
public class TransactionRepositoryTest {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Before
    public void setUp() throws Exception {
        //Ensure dependencies have been set up and injected correctly
        assertNotNull("An instance of TransactionRepository could not be created!", transactionRepository);

        //Seed the database with currencies
        Currency currency = new Currency(0l, "MILES", "MILES description", "M", LocalDateTime.now());
        currency = currencyRepository.save(currency);
        Currency currency1 = new Currency(0l, "MILES2", "MILES2 description", "M2", LocalDateTime.now());
        currency1 = currencyRepository.save(currency1);

        //Seed the database with transactions
        Transaction transaction = new Transaction(1l, TransactionType.ADD, 500d, LocalDateTime.now());
        transaction.setCurrency(currency);
        Transaction transaction1 = new Transaction(1l, TransactionType.ADD, 500d, LocalDateTime.now());
        transaction1.setCurrency(currency);
        Transaction transaction2 = new Transaction(1l, TransactionType.SUBTRACT, 50d, LocalDateTime.now());
        transaction2.setCurrency(currency);
        transactionRepository.save(transaction);
        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);

        Transaction transaction3 = new Transaction(1l, TransactionType.ADD, 500d, LocalDateTime.now());
        transaction3.setCurrency(currency1);
        Transaction transaction4 = new Transaction(1l, TransactionType.ADD, 500d, LocalDateTime.now());
        transaction4.setCurrency(currency1);
        Transaction transaction5 = new Transaction(1l, TransactionType.SUBTRACT, 150d, LocalDateTime.now());
        transaction5.setCurrency(currency1);
        transactionRepository.save(transaction3);
        transactionRepository.save(transaction4);
        transactionRepository.save(transaction5);
    }

    @After
    public void tearDown() throws Exception {
        currencyRepository = null;
        transactionRepository = null;
    }


    @Test
    public void getSummariesForCurrency() {
        var result = transactionRepository.getSummariesForCurrency(1l, 1l);
        assertTrue(result.size() == 1);

        var summary = result.stream().findFirst().get();
        assertTrue(summary.getBalance() == 950d);
    }

    @Test
    public void getSummaries() {
        var result = transactionRepository.getSummaries(1l);
        assertTrue(result.size() == 2);
    }
}