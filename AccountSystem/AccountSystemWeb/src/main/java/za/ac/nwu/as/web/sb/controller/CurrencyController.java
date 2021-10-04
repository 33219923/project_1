package za.ac.nwu.as.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.as.logic.models.request.UpsertCurrencyRequest;
import za.ac.nwu.as.translator.models.CurrencyDto;

import java.util.Date;

@RestController
@RequestMapping("currency")
public class CurrencyController {

    @PostMapping("/upsertcurrency")
    @ApiOperation(value = "Upsert Currency", notes = "Create or update an existing currency.")
    public ResponseEntity<CurrencyDto> UpsertCurrency(@RequestBody() UpsertCurrencyRequest currencyRequest) {
        var response = new CurrencyDto();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/")
    @ApiOperation(value = "Health check", notes = "Health check is used to ensure the api is up and running.")
    public ResponseEntity<String> HealthCheck() {
        return new ResponseEntity<>("The service " + this.getClass().getSimpleName() + " is up and running at " + new Date(), HttpStatus.OK);
    }
}
