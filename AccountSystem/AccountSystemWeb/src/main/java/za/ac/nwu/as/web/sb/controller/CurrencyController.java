package za.ac.nwu.as.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.as.domain.dto.CurrencyDto;
import za.ac.nwu.as.domain.exceptions.CustomException;
import za.ac.nwu.as.domain.services.GeneralResponse;
import za.ac.nwu.as.logic.services.ICurrencyService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("currency")
public class CurrencyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyController.class);

    private final ICurrencyService currencyService;

    @Autowired
    public CurrencyController(ICurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/list")
    @ApiOperation(value = "Retrieve all currencies", notes = "Returns a list of all the available currencies.")
    public ResponseEntity<GeneralResponse<List<CurrencyDto>>> list() {
        var response = new GeneralResponse<List<CurrencyDto>>();
        try {
            var data = currencyService.listAll();
            response.Data = data;
        } catch (Exception ex) {
            response.Success = false;
            response.ErrorMessage = "An error occurred, please view the logs in order to see what caused the error.";
            LOGGER.error("API error: {}", ex);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/upsert")
    @ApiOperation(value = "Upsert currency", notes = "Create or update an existing currency.")
    public ResponseEntity<GeneralResponse<CurrencyDto>> upsert(@RequestBody() CurrencyDto currencyDto) {
        var response = new GeneralResponse<CurrencyDto>();
        try {
            var data = currencyService.upsertCurrency(currencyDto);
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

    @GetMapping("/healthcheck")
    @ApiOperation(value = "Health check", notes = "Health check is used to ensure the api is up and running.")
    public ResponseEntity<String> HealthCheck() {
        LOGGER.info("Health check called for {} at {}", this.getClass().getSimpleName(), new Date());
        return new ResponseEntity<>("The service " + this.getClass().getSimpleName() + " is up and running at " + new Date(), HttpStatus.OK);
    }
}
