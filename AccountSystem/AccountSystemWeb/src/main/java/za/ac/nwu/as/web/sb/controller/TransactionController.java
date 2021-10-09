package za.ac.nwu.as.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.as.domain.dto.TransactionDto;
import za.ac.nwu.as.domain.dto.TransactionSummaryDto;
import za.ac.nwu.as.domain.exceptions.CustomException;
import za.ac.nwu.as.logic.services.ITransactionService;
import za.ac.nwu.as.domain.services.GeneralResponse;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    private final ITransactionService accountService;

    @Autowired
    public TransactionController(ITransactionService accountService) {
        this.accountService = accountService;
    }


    //TODO: Complete the api responses for every api
    @PostMapping("/add")
    @ApiOperation(value = "Increase balance", notes = "Create a new account if it does not exist and increase the remaining balance of the account.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account Updated/Created", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<TransactionDto>> add(@RequestBody() TransactionDto transactionDto) {
        var response = new GeneralResponse<TransactionDto>();
        try {
            var data = accountService.add(transactionDto);
            response.Data = data;
        } catch (CustomException ex) {
            response.Success = false;
            response.ErrorMessage = ex.getMessage();
            LOGGER.error("API error: {}", ex);
            return new ResponseEntity<>(response, ex.getResponseCode());
        } catch (Exception ex) {
            response.Success = false;
            response.ErrorMessage = "An error occurred, please view the logs in order to see what caused the error.";
            LOGGER.error("API error: {}", ex);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/subtract")
    @ApiOperation(value = "Decrease balance", notes = "Decrease the remaining balance of the account. ")
    public ResponseEntity<GeneralResponse<TransactionDto>> subtract(@RequestBody() TransactionDto transactionDto) {
        var response = new GeneralResponse<TransactionDto>();
        try {
            var data = accountService.subtract(transactionDto);
            response.Data = data;
        } catch (CustomException ex) {
            response.Success = false;
            response.ErrorMessage = ex.getMessage();
            LOGGER.error("Custom Exception: {}", ex);
            return new ResponseEntity<>(response, ex.getResponseCode());
        } catch (Exception ex) {
            response.Success = false;
            response.ErrorMessage = "An error occurred, please view the logs in order to see what caused the error.";
            LOGGER.error("API error: {}", ex);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/summaries/{memberId}")
    @ApiOperation(value = "View Balance", notes = "View the remaining account balance for an account.")
    public ResponseEntity<GeneralResponse<List<TransactionSummaryDto>>> getSummary(@PathVariable("memberId") Long memberId,
                                                                                   @RequestParam(value = "currencyId", required = false) Long currencyId) {
        var response = new GeneralResponse<List<TransactionSummaryDto>>();
        try {
            var data = accountService.getTransactionSummaries(memberId, currencyId);
            response.Data = data;
        } catch (Exception ex) {
            response.Success = false;
            response.ErrorMessage = "An error occurred, please view the logs in order to see what caused the error.";

            LOGGER.error("API error: {}", ex);

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/healthcheck")
    @ApiOperation(value = "Health check", notes = "Health check is used to ensure the api is up and running.")
    public ResponseEntity<String> HealthCheck() {
        LOGGER.info("Health check called for {} at {}", this.getClass().getSimpleName(), new Date());
        return new ResponseEntity<>("The service " + this.getClass().getSimpleName() + " is up and running at " + new Date(), HttpStatus.OK);
    }
}
