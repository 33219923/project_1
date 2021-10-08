package za.ac.nwu.as.repository.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.nwu.as.repository.config.RepositoryTestConfig;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {RepositoryTestConfig.class})
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Before
    public void setUp() throws Exception {
        //Ensure dependencies have been set up and injected correctly
        assertNotNull("An instance of TransactionRepository could not be created!", transactionRepository);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
    }
}