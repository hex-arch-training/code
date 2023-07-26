package hexarch.dms.preparation.adapter.in.web;

import hexarch.dms.preparation.domain.RevisionNotEditableException;
import hexarch.dms.preparation.domain.ValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { ValidationException.class, RevisionNotEditableException.class })
    public ResponseEntity<Object> handleValidationException(RuntimeException exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(),
                new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }
}
