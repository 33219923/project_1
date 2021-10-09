package za.ac.nwu.as.domain.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception {
    private HttpStatus responseCode;

    public CustomException(String errorMessage) {
        super(errorMessage);
    }

    public CustomException(String errorMessage, HttpStatus responseCode) {
        super(errorMessage);
        this.setResponseCode(responseCode);
    }

    public HttpStatus getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(HttpStatus responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String toString() {
        return "CustomException { " +
                " message=" + super.toString() +
                " responseCode=" + responseCode +
                " }";
    }
}
