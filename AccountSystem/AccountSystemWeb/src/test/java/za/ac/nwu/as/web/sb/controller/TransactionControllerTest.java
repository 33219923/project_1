package za.ac.nwu.as.web.sb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import za.ac.nwu.as.domain.dto.TransactionDto;
import za.ac.nwu.as.domain.dto.TransactionSummaryDto;
import za.ac.nwu.as.domain.exceptions.CustomException;
import za.ac.nwu.as.domain.services.GeneralResponse;
import za.ac.nwu.as.logic.services.impl.TransactionService;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    private static final String APP_URL = "/account-system/mvc";
    private static final String TRANSACTION_URL = APP_URL + "/transaction";

    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController accountController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void add() {
        try {
            when(transactionService.add(any(TransactionDto.class))).then(returnsFirstArg());

            String requestContent = "{\"memberId\": 1, \"currencyId\": 1 , \"amount\": 500}";

            MvcResult mvcResult = mockMvc.perform(
                            post((String.format("%s/%s", TRANSACTION_URL, "add")))
                                    .servletPath(APP_URL)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .content(requestContent)
                                    .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isCreated())
                    .andReturn();

            String contentString = mvcResult.getResponse().getContentAsString();

            GeneralResponse<TransactionDto> responseData = objectMapper.readValue(contentString, GeneralResponse.class);
            assertTrue("Response is not successful!", responseData.Success);
            assertNotNull("Data object is null", responseData.Data);

            verify(transactionService, times(1)).add(any(TransactionDto.class));
        } catch (Exception ex) {
            fail("Should not throw an error.");
        }
    }

    @Test
    public void add_with_exception_trigger() {
        try {
            when(transactionService.add(any(TransactionDto.class))).then(returnsFirstArg());

            String requestContent = "{\"memberId\": 1, \"currencyId\": 1 , \"amount\": 500}";

            MvcResult mvcResult = mockMvc.perform(
                            post((String.format("%s/%s", TRANSACTION_URL, "add")))
                                    .header("cause_exception", "true")
                                    .servletPath(APP_URL)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .content(requestContent)
                                    .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isInternalServerError())
                    .andReturn();

        } catch (Exception ex) {
            assertTrue("Should roll back transaction.",ex.getMessage().contains("Exception triggered by header, rolling back transaction..."));
        }
    }

    @Test
    public void add_without_member_id() {
        try {
            when(transactionService.add(any(TransactionDto.class))).thenThrow(new CustomException("Member id null", HttpStatus.BAD_REQUEST));

            String requestContent = "{\"currencyId\": 1 , \"amount\": 500}";

            MvcResult mvcResult = mockMvc.perform(
                            post((String.format("%s/%s", TRANSACTION_URL, "add")))
                                    .servletPath(APP_URL)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .content(requestContent)
                                    .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isBadRequest())
                    .andReturn();

            String contentString = mvcResult.getResponse().getContentAsString();

            GeneralResponse<TransactionDto> responseData = objectMapper.readValue(contentString, GeneralResponse.class);
            assertFalse("Response is not successful!", responseData.Success);
            assertNull("Data object is not null", responseData.Data);
            assertEquals("Error message is not null", "Member id null", responseData.ErrorMessage);

            verify(transactionService, times(1)).add(any(TransactionDto.class));
        } catch (Exception ex) {
            fail("Should not throw an error.");
        }
    }

    @Test
    public void add_without_currency_id() {
        try {
            when(transactionService.add(any(TransactionDto.class))).thenThrow(new CustomException("Currency id null", HttpStatus.BAD_REQUEST));

            String requestContent = "{\"memberId\": 1 , \"amount\": 500}";

            MvcResult mvcResult = mockMvc.perform(
                            post((String.format("%s/%s", TRANSACTION_URL, "add")))
                                    .servletPath(APP_URL)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .content(requestContent)
                                    .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isBadRequest())
                    .andReturn();

            String contentString = mvcResult.getResponse().getContentAsString();

            GeneralResponse<TransactionDto> responseData = objectMapper.readValue(contentString, GeneralResponse.class);
            assertFalse("Response is not successful!", responseData.Success);
            assertNull("Data object is not null", responseData.Data);
            assertEquals("Error message is not null", "Currency id null", responseData.ErrorMessage);

            verify(transactionService, times(1)).add(any(TransactionDto.class));
        } catch (Exception ex) {
            fail("Should not throw an error.");
        }
    }

    @Test
    public void add_exception_expected() {
        try {
            when(transactionService.add(any(TransactionDto.class))).thenThrow(new RuntimeException("Execution exception"));

            String requestContent = "{\"memberId\": 1, \"currencyId\": 1 , \"amount\": 500}";

            MvcResult mvcResult = mockMvc.perform(
                            post((String.format("%s/%s", TRANSACTION_URL, "add")))
                                    .servletPath(APP_URL)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .content(requestContent)
                                    .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isInternalServerError())
                    .andReturn();

            String contentString = mvcResult.getResponse().getContentAsString();

            GeneralResponse<TransactionDto> responseData = objectMapper.readValue(contentString, GeneralResponse.class);
            assertFalse("Response is not successful!", responseData.Success);
            assertNull("Data object is not null", responseData.Data);
            assertNotNull("Error message is empty or null", responseData.ErrorMessage);

            verify(transactionService, times(1)).add(any(TransactionDto.class));
        } catch (Exception ex) {
            fail("Should not throw an error.");
        }
    }

    @Test
    public void subtract() {
        try {
            when(transactionService.subtract(any(TransactionDto.class))).then(returnsFirstArg());

            String requestContent = "{\"memberId\": 1, \"currencyId\": 1 , \"amount\": 500}";

            MvcResult mvcResult = mockMvc.perform(
                            post((String.format("%s/%s", TRANSACTION_URL, "subtract")))
                                    .servletPath(APP_URL)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .content(requestContent)
                                    .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isCreated())
                    .andReturn();

            String contentString = mvcResult.getResponse().getContentAsString();

            GeneralResponse<TransactionDto> responseData = objectMapper.readValue(contentString, GeneralResponse.class);
            assertTrue("Response is not successful!", responseData.Success);
            assertNotNull("Data object is null", responseData.Data);

            verify(transactionService, times(1)).subtract(any(TransactionDto.class));
        } catch (Exception ex) {
            fail("Should not throw an error.");
        }
    }

    @Test
    public void subtract_without_member_id() {
        try {
            when(transactionService.subtract(any(TransactionDto.class))).thenThrow(new CustomException("Member id null", HttpStatus.BAD_REQUEST));

            String requestContent = "{\"currencyId\": 1, \"amount\": 500}";

            MvcResult mvcResult = mockMvc.perform(
                            post((String.format("%s/%s", TRANSACTION_URL, "subtract")))
                                    .servletPath(APP_URL)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .content(requestContent)
                                    .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isBadRequest())
                    .andReturn();

            String contentString = mvcResult.getResponse().getContentAsString();

            GeneralResponse<TransactionDto> responseData = objectMapper.readValue(contentString, GeneralResponse.class);
            assertFalse("Response is not successful!", responseData.Success);
            assertNull("Data object is not null", responseData.Data);
            assertEquals("Error message is not null", "Member id null", responseData.ErrorMessage);

            verify(transactionService, times(1)).subtract(any(TransactionDto.class));
        } catch (Exception ex) {
            fail("Should not throw an error.");
        }
    }

    @Test
    public void subtract_without_currency_id() {
        try {
            when(transactionService.subtract(any(TransactionDto.class))).thenThrow(new CustomException("Currency id null", HttpStatus.BAD_REQUEST));

            String requestContent = "{\"memberId\": 1 , \"amount\": 500}";

            MvcResult mvcResult = mockMvc.perform(
                            post((String.format("%s/%s", TRANSACTION_URL, "subtract")))
                                    .servletPath(APP_URL)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .content(requestContent)
                                    .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isBadRequest())
                    .andReturn();

            String contentString = mvcResult.getResponse().getContentAsString();

            GeneralResponse<TransactionDto> responseData = objectMapper.readValue(contentString, GeneralResponse.class);
            assertFalse("Response is not successful!", responseData.Success);
            assertNull("Data object is not null", responseData.Data);
            assertEquals("Error message is not null", "Currency id null", responseData.ErrorMessage);

            verify(transactionService, times(1)).subtract(any(TransactionDto.class));
        } catch (Exception ex) {
            fail("Should not throw an error.");
        }
    }

    @Test
    public void subtract_exception_expected() {
        try {
            when(transactionService.subtract(any(TransactionDto.class))).thenThrow(new RuntimeException("Execution exception"));

            String requestContent = "{\"memberId\": 1, \"currencyId\": 1 , \"amount\": 500}";

            MvcResult mvcResult = mockMvc.perform(
                            post((String.format("%s/%s", TRANSACTION_URL, "subtract")))
                                    .servletPath(APP_URL)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .content(requestContent)
                                    .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isInternalServerError())
                    .andReturn();

            String contentString = mvcResult.getResponse().getContentAsString();

            GeneralResponse<TransactionDto> responseData = objectMapper.readValue(contentString, GeneralResponse.class);
            assertFalse("Response is not successful!", responseData.Success);
            assertNull("Data object is not null", responseData.Data);
            assertNotNull("Error message is empty or null", responseData.ErrorMessage);

            verify(transactionService, times(1)).subtract(any(TransactionDto.class));
        } catch (Exception ex) {
            fail("Should not throw an error.");
        }
    }

    @Test
    public void getSummary_without_currency_id() {
        try {
            when(transactionService.getTransactionSummaries(any(), any())).thenReturn(new ArrayList<>());

            MvcResult mvcResult = mockMvc.perform(
                            get((String.format("%s/%s", TRANSACTION_URL, "summaries/1")))
                                    .servletPath(APP_URL)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn();

            String contentString = mvcResult.getResponse().getContentAsString();

            GeneralResponse<TransactionSummaryDto> responseData = objectMapper.readValue(contentString, GeneralResponse.class);
            assertTrue("Response is not successful!", responseData.Success);
            assertNotNull("Data object is null", responseData.Data);

            verify(transactionService, times(1)).getTransactionSummaries(any(), any());
        } catch (Exception ex) {
            fail("Should not throw an error.");
        }
    }

    @Test
    public void getSummary_with_currency_id() {
        try {
            when(transactionService.getTransactionSummaries(any(), any())).thenReturn(new ArrayList<>());

            MvcResult mvcResult = mockMvc.perform(
                            get((String.format("%s/%s", TRANSACTION_URL, "summaries/1")))
                                    .param("currencyId", "1")
                                    .servletPath(APP_URL)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn();

            String contentString = mvcResult.getResponse().getContentAsString();

            GeneralResponse<TransactionSummaryDto> responseData = objectMapper.readValue(contentString, GeneralResponse.class);
            assertTrue("Response is not successful!", responseData.Success);
            assertNotNull("Data object is null", responseData.Data);

            verify(transactionService, times(1)).getTransactionSummaries(any(), any());
        } catch (Exception ex) {
            fail("Should not throw an error.");
        }
    }

    @Test
    public void getSummary_without_member_id() {
        try {
            MvcResult mvcResult = mockMvc.perform(
                            get((String.format("%s/%s", TRANSACTION_URL, "summaries")))
                                    .servletPath(APP_URL)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isNotFound())
                    .andReturn();

            verify(transactionService, never()).getTransactionSummaries(any(), any());
        } catch (Exception ex) {
            fail("Should not throw an error.");
        }
    }

    @Test
    public void getSummary_exception_expected() {
        try {
            when(transactionService.getTransactionSummaries(any(), any())).thenThrow(new RuntimeException("Execution exception"));

            MvcResult mvcResult = mockMvc.perform(
                            get((String.format("%s/%s", TRANSACTION_URL, "summaries/1")))
                                    .servletPath(APP_URL)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isInternalServerError())
                    .andReturn();

            String contentString = mvcResult.getResponse().getContentAsString();

            GeneralResponse<TransactionSummaryDto> responseData = objectMapper.readValue(contentString, GeneralResponse.class);
            assertFalse("Response is not successful!", responseData.Success);
            assertNull("Data object is not null", responseData.Data);
            assertNotNull("Error message is empty or null", responseData.ErrorMessage);

            verify(transactionService, times(1)).getTransactionSummaries(any(), any());
        } catch (Exception ex) {
            fail("Should not throw an error.");
        }
    }

    @Test
    public void healthCheck() {
        try {
            MvcResult mvcResult = mockMvc.perform(
                            get((String.format("%s/%s", TRANSACTION_URL, "healthcheck")))
                                    .servletPath(APP_URL)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn();

            String contentString = mvcResult.getResponse().getContentAsString();
            assertFalse("Response is blank", contentString.isEmpty());
        } catch (Exception ex) {
            fail("Should not throw an error.");
        }
    }
}