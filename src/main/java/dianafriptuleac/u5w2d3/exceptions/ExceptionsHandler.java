package dianafriptuleac.u5w2d3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public dianafriptuleac.u5d8.payloads.ErrorsPayload handleBadrequest(BadRequestException ex) {
        return new dianafriptuleac.u5d8.payloads.ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public dianafriptuleac.u5d8.payloads.ErrorsPayload handleNotFound(NotFoundException ex) {
        return new dianafriptuleac.u5d8.payloads.ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public dianafriptuleac.u5d8.payloads.ErrorsPayload handleGeneric(Exception ex) {
        ex.printStackTrace();
        return new dianafriptuleac.u5d8.payloads.ErrorsPayload("Problema lato server!", LocalDateTime.now());
    }
}
