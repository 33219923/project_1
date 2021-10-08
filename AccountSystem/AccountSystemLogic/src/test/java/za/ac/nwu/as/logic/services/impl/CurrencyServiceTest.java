package za.ac.nwu.as.logic.services.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import za.ac.nwu.as.logic.services.ICurrencyService;
import za.ac.nwu.as.translator.services.ICurrencyTranslator;
import za.ac.nwu.as.translator.services.impl.CurrencyTranslator;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class CurrencyServiceTest {

    @Mock
    private CurrencyTranslator currencyTranslator;

    @InjectMocks
    private CurrencyService currencyService;

    @Before
    public void setUp() throws Exception {
        //Ensure dependencies have been set up and injected correctly
        assertNotNull("CurrencyTranslator could not be mocked correctly!", currencyTranslator);
        assertNotNull("An instance of CurrencyService could not be created!", currencyService);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void listAll() {
    }
}