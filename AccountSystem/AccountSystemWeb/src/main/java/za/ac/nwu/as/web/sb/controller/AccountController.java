package za.ac.nwu.as.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.as.logic.models.request.DecreaseAccountBalanceRequest;
import za.ac.nwu.as.logic.models.request.IncreaseAccountBalanceRequest;
import za.ac.nwu.as.translator.models.UserAccountDto;

import java.util.Date;

@RestController
@RequestMapping("account")
public class AccountController {

    @PostMapping("/increasebalance")
    @ApiOperation(value = "Increase Balance", notes = "Create a new account if it does not exist and increase the remaining balance of the account.")
    public ResponseEntity<UserAccountDto> IncreaseBalance(@RequestBody() IncreaseAccountBalanceRequest increaseRequest) {
        var response = new UserAccountDto();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/decreasebalance")
    @ApiOperation(value = "Decrease Balance", notes = "Decrease the remaining balance of the account. ")
    public ResponseEntity<UserAccountDto> DecreaseBalance(@RequestBody() DecreaseAccountBalanceRequest decreaseRequest) {
        var response = new UserAccountDto();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/viewbalance")
    @ApiOperation(value = "View Balance", notes = "View the remaining account balance for an account.")
    public ResponseEntity<UserAccountDto> ViewBalance(@RequestParam() String accountId) {
        var response = new UserAccountDto();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/")
    @ApiOperation(value = "Health check", notes = "Health check is used to ensure the api is up and running.")
    public ResponseEntity<String> HealthCheck() {
        return new ResponseEntity<>("The service " + this.getClass().getSimpleName() + " is up and running at " + new Date(), HttpStatus.OK);
    }
}
