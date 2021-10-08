package za.ac.nwu.as.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.as.domain.dto.CurrencyDto;
import za.ac.nwu.as.domain.service.GeneralResponse;
import za.ac.nwu.as.logic.services.ICurrencyService;
import za.ac.nwu.as.translator.models.request.UpsertCurrencyRequest;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("currency")
public class CurrencyController {

    private final ICurrencyService currencyService;

    @Autowired
    public CurrencyController(ICurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/list")
    @ApiOperation(value = "Retrieve All Currencies", notes = "Returns a list of all the available currencies.")
    public ResponseEntity<GeneralResponse> ListALl() {
        var response = new GeneralResponse<List<CurrencyDto>>();
        try {
            var data = currencyService.listAll();

            if (data == null) {
                response.Success = false;
                response.ErrorMessage = "Could not retrieve the currencies list.";

                //TODO: Logging

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                response.Data = data;
            }
        } catch (Exception ex) {
            response.Success = false;
            response.ErrorMessage = "An error occurred, please view the logs in order to see what caused the error.";

            //TODO: Log exception

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/upsertcurrency")
    @ApiOperation(value = "Upsert Currency", notes = "Create or update an existing currency.")
    public ResponseEntity<GeneralResponse<CurrencyDto>> UpsertCurrency(@RequestBody() CurrencyDto currencyRequest) {
        var response = new GeneralResponse<CurrencyDto>();
        try {

            //Validate request body
            if (StringUtils.isEmpty(currencyRequest.getName()) || StringUtils.isEmpty(currencyRequest.getSymbol()) || StringUtils.isEmpty(currencyRequest.getDescription())
            ) {
                response.Success = false;
                response.ErrorMessage = "Required fields cannot be empty.";

                //TODO: Logging

                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            var data = currencyService.upsertCurrency(currencyRequest);

            if (data == null) {
                response.Success = false;
                response.ErrorMessage = "The currency does not exist and thus could not be updated.";

                //TODO: Logging

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                response.Data = data;
            }
        } catch (Exception ex) {
            response.Success = false;
            response.ErrorMessage = "An error occurred, please view the logs in order to see what caused the error.";

            //TODO: Log exception

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/healthcheck")
    @ApiOperation(value = "Health check", notes = "Health check is used to ensure the api is up and running.")
    public ResponseEntity<String> HealthCheck() {
        return new ResponseEntity<>("The service " + this.getClass().getSimpleName() + " is up and running at " + new Date(), HttpStatus.OK);
    }
}
