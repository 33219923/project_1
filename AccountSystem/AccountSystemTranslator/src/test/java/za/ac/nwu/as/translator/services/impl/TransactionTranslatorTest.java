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
    public void createTransaction() {
        //        GeneralResponse<String> expectedResponseObject = new GeneralResponse<>();
//        String expectedResponse = objectMapper.writeValueAsString(expectedResponseObject);
//
//        when(accountService.decreaseBalance(eq(new DecreaseAccountBalanceRequest()))).then(returnsFirstArg());
//
//        MvcResult mvcResult = mockMvc.perform(
//                get((String.format("%s/%s", ACCOUNT_URL, "endpoint")))
//                    .servletPath(APP_URL)
//                    .accept(MediaType.APPLICATION_JSON)
//                    .content(objectMapper.writeValueAsString(new DecreaseAccountBalanceRequest())) //Add correct content
//                    .contentType(MediaType.APPLICATION_JSON)
//                ).andExpect(status().isOk())
//                .andReturn();
//
//        verify(accountService, never()).viewBalance(anyString()); //Check correct method check to ensure errors etc
//        verify(accountService, times(1)).viewBalance(anyString()); //Check correct method
//        assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getTransactionSummaries() {
    }
}