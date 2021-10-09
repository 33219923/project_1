package za.ac.nwu.as.translator.services.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.nwu.as.domain.dto.CurrencyDto;
import za.ac.nwu.as.domain.dto.TransactionDto;
import za.ac.nwu.as.domain.dto.TransactionSummaryDto;
import za.ac.nwu.as.domain.enums.TransactionType;
import za.ac.nwu.as.domain.persistence.Transaction;
import za.ac.nwu.as.repository.persistence.TransactionRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class TransactionTranslatorTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionTranslator transactionTranslator;

    @Before
    public void setUp() throws Exception {
        //Ensure dependencies have been set up and injected correctly
        assertNotNull("TransactionRepository could not be mocked!", transactionRepository);
        assertNotNull("An instance of TransactionTranslator could not be created!", transactionTranslator);
    }

    @After
    public void tearDown() throws Exception {
        transactionRepository = null;
        transactionTranslator = null;
    }

    @Test
    public void createTransaction() {
        var transaction = new TransactionDto(0l, 1l, 1l, 500d);
        transaction.setType(TransactionType.ADD);
        var currency = new CurrencyDto(1l, "MILES", "MILES description", "M");
        when(transactionRepository.save(any(Transaction.class))).then(returnsFirstArg());

        var result = transactionTranslator.createTransaction(transaction, currency);
        assertNotNull("Result should not be null", result);
        assertEquals("Currency id should equal to the currencyId passed in", currency.getId(), result.getCurrencyId());
        assertEquals("Type should be set to ADD", result.getType(), TransactionType.ADD);
        assertNotNull("Created date should be set", result.getCreatedDate());

        verify(transactionRepository, times(1)).save(any());
    }

    @Test
    public void createTransaction_exception_expected() {
        boolean ensureError = false;
        try {
            var transaction = new TransactionDto(0l, 1l, 1l, 500d);
            var currency = new CurrencyDto(1l, "MILES", "MILES description", "M");
            when(transactionRepository.save(any(Transaction.class))).thenThrow(new RuntimeException("Database exception!"));

            transactionTranslator.createTransaction(transaction, currency);
        } catch (Exception ex) {
            ensureError = true;
        }

        assertTrue("Should have thrown an exception!", ensureError);
    }

    @Test
    public void getTransactionSummaries() {
        List<TransactionSummaryDto> singleCurrencyList = new ArrayList<>();
        singleCurrencyList.add(new TransactionSummaryDto(1l, 500d, "M"));

        List<TransactionSummaryDto> fullList = new ArrayList<>();
        fullList.add(new TransactionSummaryDto(1l, 500d, "M"));
        fullList.add(new TransactionSummaryDto(1l, 500d, "M"));

        when(transactionRepository.getSummariesForCurrency(anyLong(), anyLong())).thenReturn(singleCurrencyList);
        when(transactionRepository.getSummaries(anyLong())).thenReturn(fullList);

        var result = transactionTranslator.getTransactionSummaries(1l, 0l);
        assertTrue("The list should contain more than one element.", result.size() == 2);

        result = transactionTranslator.getTransactionSummaries(1l, 1l);
        assertTrue("The list should contain no more than one element.", result.size() == 1);

        verify(transactionRepository, times(1)).getSummariesForCurrency(anyLong(), anyLong());
        verify(transactionRepository, times(1)).getSummaries(anyLong());
    }

    @Test
    public void getTransactionSummaries_currency_null() {
        List<TransactionSummaryDto> singleCurrencyList = new ArrayList<>();
        singleCurrencyList.add(new TransactionSummaryDto(1l, 500d, "M"));

        List<TransactionSummaryDto> fullList = new ArrayList<>();
        fullList.add(new TransactionSummaryDto(1l, 500d, "M"));
        fullList.add(new TransactionSummaryDto(1l, 500d, "M"));

        when(transactionRepository.getSummariesForCurrency(anyLong(), anyLong())).thenReturn(singleCurrencyList);
        when(transactionRepository.getSummaries(anyLong())).thenReturn(fullList);

        var result = transactionTranslator.getTransactionSummaries(1l, null);
        assertTrue("The list should contain more than one element.", result.size() == 2);

        verify(transactionRepository, times(1)).getSummaries(anyLong());
    }

    @Test
    public void getTransactionSummaries_exception_exptected() {
        boolean ensureError = false;
        try {
            when(transactionRepository.getSummariesForCurrency(anyLong(), anyLong())).thenThrow(new RuntimeException("Database exception!"));
            when(transactionRepository.getSummaries(anyLong())).thenThrow(new RuntimeException("Database exception!"));

            List<TransactionSummaryDto> singleCurrencyList = new ArrayList<>();
            singleCurrencyList.add(new TransactionSummaryDto(1l, 500d, "M"));

            List<TransactionSummaryDto> fullList = new ArrayList<>();
            fullList.add(new TransactionSummaryDto(1l, 500d, "M"));
            fullList.add(new TransactionSummaryDto(1l, 500d, "M"));

            transactionTranslator.getTransactionSummaries(1l, null);

        } catch (Exception ex) {
            ensureError = true;
        }

        assertTrue("Should have thrown an exception!", ensureError);
    }
}