package za.ac.nwu.as.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.as.logic.services.CurrencyService;
import za.ac.nwu.as.translator.models.request.UpsertCurrencyRequest;
import za.ac.nwu.as.domain.service.GeneralResponse;
import za.ac.nwu.as.translator.models.response.CurrencyDto;

import java.util.Date;

@RestController
@RequestMapping("currency")
public class CurrencyController {

    @PostMapping("/upsertcurrency")
    @ApiOperation(value = "Upsert Currency", notes = "Create or update an existing currency.")
    public ResponseEntity<GeneralResponse<CurrencyDto>> UpsertCurrency(@RequestBody() UpsertCurrencyRequest currencyRequest) {
        var response = new GeneralResponse<CurrencyDto>();
        try {

            //Validate request body
            if (StringUtils.isEmpty(currencyRequest.Name) || StringUtils.isEmpty(currencyRequest.Name) || StringUtils.isEmpty(currencyRequest.Name)
            ) {
                response.Success = false;
                response.ErrorMessage = "Required fields cannot be empty.";

                //TODO: Logging

                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            var data = CurrencyService.UpsertCurrency(currencyRequest);

            if(data == null)
            {
                response.Success = false;
                response.ErrorMessage = "The currency does not exist and thus could not be updated.";

                //TODO: Logging

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            else{
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

    @GetMapping("/")
    @ApiOperation(value = "Health check", notes = "Health check is used to ensure the api is up and running.")
    public ResponseEntity<String> HealthCheck() {
        return new ResponseEntity<>("The service " + this.getClass().getSimpleName() + " is up and running at " + new Date(), HttpStatus.OK);
    }
}
