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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.spring.web.json.JsonSerializer;
import za.ac.nwu.as.domain.service.GeneralResponse;
import za.ac.nwu.as.logic.services.impl.AccountService;
import za.ac.nwu.as.translator.models.request.DecreaseAccountBalanceRequest;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    private static final String APP_URL = "/account-system/mvc";
    private static final String ACCOUNT_URL = APP_URL + "/account";

    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() throws Exception {
        GeneralResponse<String> expectedResponseObject = new GeneralResponse<>();
        String expectedResponse = objectMapper.writeValueAsString(expectedResponseObject);

        when(accountService.decreaseBalance(eq(new DecreaseAccountBalanceRequest()))).then(returnsFirstArg());

        MvcResult mvcResult = mockMvc.perform(
                get((String.format("%s/%s", ACCOUNT_URL, "endpoint")))
                    .servletPath(APP_URL)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(new DecreaseAccountBalanceRequest())) //Add correct content
                    .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andReturn();

        verify(accountService, never()).viewBalance(anyString()); //Check correct method check to ensure errors etc
        verify(accountService, times(1)).viewBalance(anyString()); //Check correct method
        assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString());
    }

}