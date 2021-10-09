package za.ac.nwu.as.logic.services.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.nwu.as.domain.dto.CurrencyDto;
import za.ac.nwu.as.domain.dto.TransactionDto;
import za.ac.nwu.as.domain.dto.TransactionSummaryDto;
import za.ac.nwu.as.domain.enums.TransactionType;
import za.ac.nwu.as.domain.exceptions.CustomException;
import za.ac.nwu.as.translator.services.impl.CurrencyTranslator;
import za.ac.nwu.as.translator.services.impl.TransactionTranslator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class TransactionServiceTest {

    @Mock
    private TransactionTranslator transactionTranslator;

    @Mock
    private CurrencyTranslator currencyTranslator;

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
        transactionTranslator = null;
        transactionService = null;
    }

    @Test
    public void add_with_member_id_and_currency_id() {
        try {
            var transaction = new TransactionDto(0l, 1l, 1l, 500d);
            var currency = new CurrencyDto(1l, "MILES", "MILES description", "M");

            when(currencyTranslator.getCurrencyById(eq(currency.getId()))).thenReturn(currency);
            when(transactionTranslator.createTransaction(any(TransactionDto.class), any(CurrencyDto.class))).then(returnsFirstArg());

            var result = transactionService.add(transaction);
            assertNotNull("Result should not be null", result);
            assertEquals("Currency id should equal to the currencyId passed in", currency.getId(), result.getCurrencyId());
            assertEquals("Type should be set to ADD", result.getType(), TransactionType.ADD);
            assertNotNull("Created date should be set", result.getCreatedDate());

            verify(currencyTranslator, times(1)).getCurrencyById(eq(currency.getId()));
            verify(transactionTranslator, times(1)).createTransaction(any(TransactionDto.class),any(CurrencyDto.class));
        } catch (CustomException e) {
            fail();
        }
    }

    @Test
    public void add_with_member_id_without_currency_id() {
        boolean ensureError = false;
        try {
            var transaction = new TransactionDto(0l, 1l, 0l, 500d);
            var result = transactionService.add(transaction);
        } catch (CustomException e) {
            ensureError = true;
            assertEquals(e.getResponseCode(), HttpStatus.BAD_REQUEST);
        }
        assertTrue("Should have thrown an exception!", ensureError);
    }

    @Test
    public void add_without_member_id_without_currency_id() {
        boolean ensureError = false;
        try {
            var transaction = new TransactionDto(0l, 0l, 0l, 500d);
            var result = transactionService.add(transaction);
        } catch (CustomException e) {
            ensureError = true;
            assertEquals(e.getResponseCode(), HttpStatus.BAD_REQUEST);
        }
        assertTrue("Should have thrown an exception!", ensureError);
    }

    @Test
    public void add_without_member_id_with_currency_id() {
        boolean ensureError = false;
        try {
            var transaction = new TransactionDto(0l, 0l, 1l, 500d);
            var result = transactionService.add(transaction);
        } catch (CustomException e) {
            ensureError = true;
            assertEquals(e.getResponseCode(), HttpStatus.BAD_REQUEST);
        }
        assertTrue("Should have thrown an exception!", ensureError);
    }

    @Test
    public void subtract_with_member_id_and_currency_id() {
        try {
            var transaction = new TransactionDto(0l, 1l, 1l, 500d);
            var currency = new CurrencyDto(1l, "MILES", "MILES description", "M");

            when(currencyTranslator.getCurrencyById(eq(currency.getId()))).thenReturn(currency);
            when(transactionTranslator.createTransaction(any(TransactionDto.class), any(CurrencyDto.class))).then(returnsFirstArg());

            var result = transactionService.subtract(transaction);
            assertNotNull("Result should not be null", result);
            assertEquals("Currency id should equal to the currencyId passed in", currency.getId(), result.getCurrencyId());
            assertEquals("Type should be set to SUBTRACT", result.getType(), TransactionType.SUBTRACT);
            assertNotNull("Created date should be set", result.getCreatedDate());

            verify(currencyTranslator, times(1)).getCurrencyById(eq(currency.getId()));
            verify(transactionTranslator, times(1)).createTransaction(any(TransactionDto.class),any(CurrencyDto.class));
        } catch (CustomException e) {
            fail();
        }
    }

    @Test
    public void subtract_with_member_id_without_currency_id() {
        boolean ensureError = false;
        try {
            var transaction = new TransactionDto(0l, 1l, 0l, 500d);
            var result = transactionService.subtract(transaction);
        } catch (CustomException e) {
            ensureError = true;
            assertEquals(e.getResponseCode(), HttpStatus.BAD_REQUEST);
        }
        assertTrue("Should have thrown an exception!", ensureError);
    }

    @Test
    public void subtract_without_member_id_without_currency_id() {
        boolean ensureError = false;
        try {
            var transaction = new TransactionDto(0l, 0l, 0l, 500d);
            var result = transactionService.subtract(transaction);
        } catch (CustomException e) {
            ensureError = true;
            assertEquals(e.getResponseCode(), HttpStatus.BAD_REQUEST);
        }
        assertTrue("Should have thrown an exception!", ensureError);
    }

    @Test
    public void subtract_without_member_id_with_currency_id() {
        boolean ensureError = false;
        try {
            var transaction = new TransactionDto(0l, 0l, 1l, 500d);
            var result = transactionService.subtract(transaction);
        } catch (CustomException e) {
            ensureError = true;
            assertEquals(e.getResponseCode(), HttpStatus.BAD_REQUEST);
        }
        assertTrue("Should have thrown an exception!", ensureError);
    }

    @Test
    public void getTransactionSummaries() {
        List<TransactionSummaryDto> list = new ArrayList<>();
        list.add(new TransactionSummaryDto(1l, 500d, "M"));
        when(transactionTranslator.getTransactionSummaries(anyLong(), anyLong())).thenReturn(list);

        var result = transactionService.getTransactionSummaries(1l,1l);
        assertFalse("The list should not be empty.", result.isEmpty());

        verify(transactionTranslator, times(1)).getTransactionSummaries(anyLong(), anyLong());
    }
}