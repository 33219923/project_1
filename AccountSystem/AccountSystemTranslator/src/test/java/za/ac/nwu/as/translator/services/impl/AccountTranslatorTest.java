package za.ac.nwu.as.translator.services.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.nwu.as.repository.persistence.AccountRepository;
import za.ac.nwu.as.repository.persistence.CurrencyRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class AccountTranslatorTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountTranslator accountTranslator;

    @Before
    public void setUp() throws Exception {
        //Ensure dependencies have been set up and injected correctly
        assertNotNull("AccountRepository could not be mocked!", accountRepository);
        assertNotNull("An instance of AccountTranslator could not be created!", accountTranslator);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
    }
}