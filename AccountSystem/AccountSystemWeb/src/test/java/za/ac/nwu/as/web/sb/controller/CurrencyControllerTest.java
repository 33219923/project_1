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
import za.ac.nwu.as.domain.dto.CurrencyDto;
import za.ac.nwu.as.domain.dto.TransactionDto;
import za.ac.nwu.as.domain.exceptions.CustomException;
import za.ac.nwu.as.domain.services.GeneralResponse;
import za.ac.nwu.as.logic.services.impl.CurrencyService;
import za.ac.nwu.as.logic.services.impl.TransactionService;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyControllerTest {
    private static final String APP_URL = "/account-system/mvc";
    private static final String CURRENCY_URL = APP_URL + "/currency";

    @Mock
    private CurrencyService currencyService;

    @InjectMocks
    private CurrencyController currencyController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(currencyController).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void list() {
        try {
            when(currencyService.listAll()).thenReturn(new ArrayList<>());

            MvcResult mvcResult = mockMvc.perform(
                            get((String.format("%s/%s", CURRENCY_URL, "list")))
                                    .servletPath(APP_URL)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn();

            String contentString = mvcResult.getResponse().getContentAsString();

            GeneralResponse<TransactionDto> responseData = objectMapper.readValue(contentString, GeneralResponse.class);
            assertTrue("Response is not successful!", responseData.Success);
            assertNotNull("Data object is null", responseData.Data);

            verify(currencyService, times(1)).listAll();
        } catch (Exception ex) {
            fail("Should not throw an error.");
        }
    }

    @Test
    public void list_exception_expected() {
        try {
            when(currencyService.listAll()).thenThrow(new RuntimeException("Execution exception"));

            MvcResult mvcResult = mockMvc.perform(
                            get((String.format("%s/%s", CURRENCY_URL, "list")))
                                    .servletPath(APP_URL)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isInternalServerError())
                    .andReturn();

            String contentString = mvcResult.getResponse().getContentAsString();

            GeneralResponse<TransactionDto> responseData = objectMapper.readValue(contentString, GeneralResponse.class);
            assertFalse("Response is not successful!", responseData.Success);
            assertNull("Data object is not null", responseData.Data);
            assertNotNull("Error message is empty or null", responseData.ErrorMessage);

            verify(currencyService, times(1)).listAll();
        } catch (Exception ex) {
            fail("Should not throw an error.");
        }
    }

    @Test
    public void upsert() {
        try {
            when(currencyService.upsertCurrency(any(CurrencyDto.class))).then(returnsFirstArg());

            String requestContent = "{\"currencyId\": 1, \"name\": \"MILES\", \"description\": \"Miles description\"," +
                    "\"symbol\":\"M\"}";

            MvcResult mvcResult = mockMvc.perform(
                            post((String.format("%s/%s", CURRENCY_URL, "upsert")))
                                    .servletPath(APP_URL)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .content(requestContent)
                                    .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isCreated())
                    .andReturn();

            String contentString = mvcResult.getResponse().getContentAsString();

            GeneralResponse<CurrencyDto> responseData = objectMapper.readValue(contentString, GeneralResponse.class);
            assertTrue("Response is not successfull!", responseData.Success);
            assertNotNull("Data object is null", responseData.Data);

            verify(currencyService, times(1)).upsertCurrency(any(CurrencyDto.class));
        } catch (Exception ex) {
            fail("Should not throw an error.");
        }
    }

    @Test
    public void upsert_without_currency_id() {
        try {
            when(currencyService.upsertCurrency(any(CurrencyDto.class))).then(returnsFirstArg());

            String requestContent = "{\"name\": \"MILES\", \"description\": \"Miles description\"," +
                    "\"symbol\":\"M\"}";

            MvcResult mvcResult = mockMvc.perform(
                            post((String.format("%s/%s", CURRENCY_URL, "upsert")))
                                    .servletPath(APP_URL)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .content(requestContent)
                                    .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isCreated())
                    .andReturn();

            String contentString = mvcResult.getResponse().getContentAsString();

            GeneralResponse<CurrencyDto> responseData = objectMapper.readValue(contentString, GeneralResponse.class);
            assertTrue("Response is not successfull!", responseData.Success);
            assertNotNull("Data object is null", responseData.Data);

            verify(currencyService, times(1)).upsertCurrency(any(CurrencyDto.class));
        } catch (Exception ex) {
            fail("Should not throw an error.");
        }
    }

    @Test
    public void upsert_without_name() {
        try {
            when(currencyService.upsertCurrency(any(CurrencyDto.class))).thenThrow(new CustomException("Name is null", HttpStatus.BAD_REQUEST));

            String requestContent = "{\"description\": \"Miles description\"," +
                    "\"symbol\":\"M\"}";

            MvcResult mvcResult = mockMvc.perform(
                            post((String.format("%s/%s", CURRENCY_URL, "upsert")))
                                    .servletPath(APP_URL)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .content(requestContent)
                                    .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isBadRequest())
                    .andReturn();

            String contentString = mvcResult.getResponse().getContentAsString();

            GeneralResponse<CurrencyDto> responseData = objectMapper.readValue(contentString, GeneralResponse.class);
            assertFalse("Response is not successful!", responseData.Success);
            assertNull("Data object is not null", responseData.Data);
            assertEquals("Error message is not null", "Name is null", responseData.ErrorMessage);

            verify(currencyService, times(1)).upsertCurrency(any(CurrencyDto.class));
        } catch (Exception ex) {
            fail("Should not throw an error.");
        }
    }

    @Test
    public void upsert_without_description() {
        try {
            when(currencyService.upsertCurrency(any(CurrencyDto.class))).thenThrow(new CustomException("Description is null", HttpStatus.BAD_REQUEST));

            String requestContent = "{\"name\": \"MILES\", " +
                    "\"symbol\":\"M\"}";

            MvcResult mvcResult = mockMvc.perform(
                            post((String.format("%s/%s", CURRENCY_URL, "upsert")))
                                    .servletPath(APP_URL)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .content(requestContent)
                                    .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isBadRequest())
                    .andReturn();

            String contentString = mvcResult.getResponse().getContentAsString();

            GeneralResponse<CurrencyDto> responseData = objectMapper.readValue(contentString, GeneralResponse.class);
            assertFalse("Response is not successful!", responseData.Success);
            assertNull("Data object is not null", responseData.Data);
            assertEquals("Error message is not null", "Description is null", responseData.ErrorMessage);

            verify(currencyService, times(1)).upsertCurrency(any(CurrencyDto.class));
        } catch (Exception ex) {
            fail("Should not throw an error.");
        }
    }

    @Test
    public void upsert_without_symbol() {
        try {
            when(currencyService.upsertCurrency(any(CurrencyDto.class))).thenThrow(new CustomException("Symbol is null", HttpStatus.BAD_REQUEST));

            String requestContent = "{\"name\": \"MILES\", \"description\": \"Miles description\"}";

            MvcResult mvcResult = mockMvc.perform(
                            post((String.format("%s/%s", CURRENCY_URL, "upsert")))
                                    .servletPath(APP_URL)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .content(requestContent)
                                    .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isBadRequest())
                    .andReturn();

            String contentString = mvcResult.getResponse().getContentAsString();

            GeneralResponse<CurrencyDto> responseData = objectMapper.readValue(contentString, GeneralResponse.class);
            assertFalse("Response is not successful!", responseData.Success);
            assertNull("Data object is not null", responseData.Data);
            assertEquals("Error message is not null", "Symbol is null", responseData.ErrorMessage);

            verify(currencyService, times(1)).upsertCurrency(any(CurrencyDto.class));
        } catch (Exception ex) {
            fail("Should not throw an error.");
        }
    }

    @Test
    public void upsert_exception_expected() {
        try {
            when(currencyService.upsertCurrency(any(CurrencyDto.class))).thenThrow(new RuntimeException("Execution exception"));

            String requestContent = "{\"name\": \"MILES\", \"description\": \"Miles description\"," +
                    "\"symbol\":\"M\"}";

            MvcResult mvcResult = mockMvc.perform(
                            post((String.format("%s/%s", CURRENCY_URL, "upsert")))
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

            verify(currencyService, times(1)).upsertCurrency(any(CurrencyDto.class));
        } catch (Exception ex) {
            fail("Should not throw an error.");
        }
    }

    @Test
    public void healthCheck() {
        try {
            MvcResult mvcResult = mockMvc.perform(
                            get((String.format("%s/%s", CURRENCY_URL, "healthcheck")))
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