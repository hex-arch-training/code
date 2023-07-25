package hexarch.dms.verification.adapter.in.web;

import hexarch.dms.verification.application.VerificationRequestNotFoundException;
import hexarch.dms.verification.domain.VerificationStatusException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class VerificationRestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {VerificationStatusException.class})
    public ResponseEntity<Object> handleVerificationException(RuntimeException exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(),
                new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }

    @ExceptionHandler(value = {VerificationRequestNotFoundException.class})
    public ResponseEntity<Object> handleVerificationException(VerificationRequestNotFoundException exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}