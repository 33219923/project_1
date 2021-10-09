package za.ac.nwu.as.translator.services.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.nwu.as.repository.persistence.TransactionRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class TransactionTranslatorTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionTranslator transactionTranslator;

    @Before
    public void setUp() throws Exception {
        //Ensure dependencies have been set up and injected correctly
        assertNotNull("TransactionRepository could not be mocked!", transactionRepository );
        assertNotNull("An instance of TransactionTranslator could not be created!", transactionTranslator);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
    }
}