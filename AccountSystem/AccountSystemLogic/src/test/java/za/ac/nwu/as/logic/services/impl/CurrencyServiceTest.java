package za.ac.nwu.as.logic.services.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.nwu.as.domain.dto.CurrencyDto;
import za.ac.nwu.as.domain.exceptions.CustomException;
import za.ac.nwu.as.translator.services.impl.CurrencyTranslator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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
        currencyTranslator = null;
        currencyService = null;
    }

    @Test
    public void listAll() {
        List<CurrencyDto> list = new ArrayList<>();
        list.add(new CurrencyDto(1l, "MILES", "MILES description", "M"));
        when(currencyTranslator.getAllCurrencies()).thenReturn(list);

        var result = currencyService.listAll();
        assertFalse("The list should not be empty.", result.isEmpty());

        verify(currencyTranslator, times(1)).getAllCurrencies();
    }

    @Test
    public void addNewCurrency_noException_expected() {
        try {
            var currency = new CurrencyDto(0l, "MILES", "MILES description", "M");
            when(currencyTranslator.upsert(eq(currency))).thenReturn(currency);

            var result = currencyService.upsertCurrency(currency);
            assertEquals("Currencies are not equal", currency, result);

            verify(currencyTranslator, times(1)).upsert(eq(currency));
        } catch (CustomException e) {
            fail();
        }
    }

    @Test
    public void updateCurrency_noException_expected() {
        try {
            var originalCurrency = new CurrencyDto(1l, "MILES", "MILES description", "M");
            originalCurrency.setCreatedDate(LocalDateTime.now().minusDays(1));

            var updatedCurrency = new CurrencyDto(1l, "MILES2", "MILES2 description", "M2");

            var expectedResult = new CurrencyDto(updatedCurrency.getId(), updatedCurrency.getName(), updatedCurrency.getDescription(), updatedCurrency.getSymbol());
            expectedResult.setCreatedDate(originalCurrency.getCreatedDate());

            when(currencyTranslator.upsert(eq(expectedResult))).thenReturn(expectedResult);
            when(currencyTranslator.getCurrencyById(eq(updatedCurrency.getId()))).thenReturn(originalCurrency);

            var result = currencyService.upsertCurrency(updatedCurrency);

            assertNotNull("Service should return an object that is not null!", result);
            assertEquals("Created date should not be modified!", originalCurrency.getCreatedDate(), result.getCreatedDate());
            assertEquals("Name should be updated", updatedCurrency.getName(), result.getName());
            assertEquals("Description should be updated", updatedCurrency.getDescription(), result.getDescription());
            assertEquals("Symbol should be updated", updatedCurrency.getSymbol(), result.getSymbol());
            assertEquals("Result should be updated to expected result", expectedResult, result);

            verify(currencyTranslator, times(1)).upsert(eq(expectedResult));
            verify(currencyTranslator, times(1)).getCurrencyById(eq(updatedCurrency.getId()));
        } catch (CustomException e) {
            fail();
        }
    }
}