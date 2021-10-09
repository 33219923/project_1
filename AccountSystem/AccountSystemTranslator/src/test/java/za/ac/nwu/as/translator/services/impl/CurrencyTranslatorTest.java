package za.ac.nwu.as.translator.services.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.nwu.as.domain.dto.CurrencyDto;
import za.ac.nwu.as.domain.exceptions.CustomException;
import za.ac.nwu.as.domain.persistence.Currency;
import za.ac.nwu.as.repository.persistence.CurrencyRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
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
        currencyRepository = null;
        currencyTranslator = null;
    }

    @Test
    public void getAllCurrencies_no_exceptions() {
        List<Currency> list = new ArrayList<>();
        list.add(new Currency(1l, "MILES", "Miles desc", "M", LocalDateTime.now()));
        when(currencyRepository.findAll()).thenReturn(list);

        var result = currencyTranslator.getAllCurrencies();
        assertFalse("The list should not be empty.", result.isEmpty());

        verify(currencyRepository, times(1)).findAll();
    }

    @Test
    public void getAllCurrencies_with_exception() {
        boolean ensureError = false;
        try {
            when(currencyRepository.findAll()).thenThrow(new RuntimeException("Mocking database exception!"));
            currencyTranslator.getAllCurrencies();
        } catch (Exception ex) {
            ensureError = true;
        }
        assertTrue("Should have thrown an exception!", ensureError);
    }

    @Test
    public void getCurrencyById_found() {
        Currency currency = new Currency(1l, "MILES", "Miles desc", "M", LocalDateTime.now());
        when(currencyRepository.findById(eq(currency.getId()))).thenReturn(java.util.Optional.of(currency));

        try {
            var result = currencyTranslator.getCurrencyById(currency.getId());
            assertNotNull("The result should not be empty.", result);
        } catch (Exception ex) {
            fail("Should not throw an exception!");
        }

        verify(currencyRepository, times(1)).findById(eq(currency.getId()));
    }

    @Test
    public void getCurrencyById_not_found() {
        boolean ensureError = false;
        try {
            Currency currency = new Currency(1l, "MILES", "Miles desc", "M", LocalDateTime.now());
            when(currencyRepository.findById(eq(currency.getId()))).thenReturn(Optional.ofNullable(null));
            currencyTranslator.getCurrencyById(currency.getId());
        } catch (CustomException ex) {
            ensureError = true;
            assertEquals("Should throw custom error with NOT FOUND status!", ex.getResponseCode(), HttpStatus.NOT_FOUND);
        }

        assertTrue("Should have thrown an exception!", ensureError);
    }

    @Test
    public void getCurrencyById_exception_expected() {
        boolean ensureError = false;
        try {
            when(currencyRepository.findById(anyLong())).thenThrow(new RuntimeException("Database exception!"));
            currencyTranslator.getCurrencyById(1l);
        } catch (Exception ex) {
            ensureError = true;
        }

        assertTrue("Should have thrown an exception!", ensureError);
    }

    @Test
    public void upsert() {
        var currency = new CurrencyDto(1l, "MILES", "MILES description", "M");
        when(currencyRepository.save(any(Currency.class))).then(returnsFirstArg());

        var result = currencyTranslator.upsert(currency);
        assertEquals("Currencies are not equal", currency, result);

        verify(currencyRepository, times(1)).save(any());
    }

    @Test
    public void upsert_exception_expected() {
        boolean ensureError = false;
        try {
            var currency = new CurrencyDto(1l, "MILES", "MILES description", "M");
            when(currencyRepository.save(any(Currency.class))).thenThrow(new RuntimeException("Database exception!"));

            currencyTranslator.upsert(currency);
        } catch (Exception ex) {
            ensureError = true;
        }

        assertTrue("Should have thrown an exception!", ensureError);
    }
}