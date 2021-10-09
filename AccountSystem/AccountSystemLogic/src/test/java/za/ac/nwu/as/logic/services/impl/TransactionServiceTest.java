package za.ac.nwu.as.logic.services.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.nwu.as.translator.services.impl.TransactionTranslator;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class TransactionServiceTest {

    @Mock
    private TransactionTranslator transactionTranslator;

    @InjectMocks
    private TransactionService transactionService;

    @Before
    public void setUp() throws Exception {
        //Ensure dependencies have been set up and injected correctly
        assertNotNull("TransactionTranslator could not be mocked correctly!", transactionTranslator);
        assertNotNull("An instance of TransactionService could not created!", transactionService);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void increaseBalance() {
    }
}