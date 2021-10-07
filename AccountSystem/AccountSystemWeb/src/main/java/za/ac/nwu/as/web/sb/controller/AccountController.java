package za.ac.nwu.as.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.as.logic.AccountLogic;
import za.ac.nwu.as.translator.models.request.DecreaseAccountBalanceRequest;
import za.ac.nwu.as.translator.models.request.IncreaseAccountBalanceRequest;
import za.ac.nwu.as.domain.service.GeneralResponse;
import za.ac.nwu.as.translator.models.response.UserAccountDto;

import java.util.Date;

@RestController
@RequestMapping("account")
public class AccountController {

    @PostMapping("/increasebalance")
    @ApiOperation(value = "Increase Balance", notes = "Create a new account if it does not exist and increase the remaining balance of the account.")
    public ResponseEntity<GeneralResponse<UserAccountDto>> IncreaseBalance(@RequestBody() IncreaseAccountBalanceRequest increaseRequest) {
        var response = new GeneralResponse<UserAccountDto>();
        try {

            //Validate request body
            if (StringUtils.isEmpty(increaseRequest.AccountId) || increaseRequest.Amount != 0) {
                response.Success = false;
                response.ErrorMessage = "Required fields cannot be empty.";

                //TODO: Logging

                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            var data = AccountLogic.IncreaseBalance(increaseRequest);

            if(data == null)
            {
                response.Success = false;
                response.ErrorMessage = "The transaction could not be performed.";

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

    @PostMapping("/decreasebalance")
    @ApiOperation(value = "Decrease Balance", notes = "Decrease the remaining balance of the account. ")
    public ResponseEntity<GeneralResponse<UserAccountDto>> DecreaseBalance(@RequestBody() DecreaseAccountBalanceRequest decreaseRequest) {
        var response = new GeneralResponse<UserAccountDto>();
        try {

            //Validate request body
            if (StringUtils.isEmpty(decreaseRequest.AccountId) || decreaseRequest.Amount != 0) {
                response.Success = false;
                response.ErrorMessage = "Required fields cannot be empty.";

                //TODO: Logging

                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            var data = AccountLogic.DecreaseBalance(decreaseRequest);

            if(data == null)
            {
                response.Success = false;
                response.ErrorMessage = "The transaction could not be performed. Either the account does not exist or the account does not have sufficient credit.";

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

    @GetMapping("/viewbalance")
    @ApiOperation(value = "View Balance", notes = "View the remaining account balance for an account.")
    public ResponseEntity<GeneralResponse<UserAccountDto>> ViewBalance(@RequestParam() String accountId) {
        var response = new GeneralResponse<UserAccountDto>();
        try {
            //Validate request params
            if (StringUtils.isEmpty(accountId)) {
                response.Success = false;
                response.ErrorMessage = "The account id cannot be empty.";

                //TODO: Logging

                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            var data = AccountLogic.ViewBalance(accountId);

            if(data == null)
            {
                response.Success = false;
                response.ErrorMessage = "The account does not exist.";

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
