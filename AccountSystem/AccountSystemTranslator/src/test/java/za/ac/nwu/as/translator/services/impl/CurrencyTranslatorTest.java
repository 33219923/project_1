package za.ac.nwu.as.translator.services.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import za.ac.nwu.as.repository.persistence.CurrencyRepository;

import static org.junit.Assert.*;

public class CurrencyTranslatorTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private CurrencyTranslator currencyTranslator;

    @Before
    public void setUp() throws Exception {
        //Ensure dependencies have been set up and injected correctly
        assertNotNull("CurrencyRepository could not be mocked!", currencyRepository);
        assertNotNull("An instance of CurrencyTranslator could not be created!", currencyTranslator);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllCurrencies() {
    }
}