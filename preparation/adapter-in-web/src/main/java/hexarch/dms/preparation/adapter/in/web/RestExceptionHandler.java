package hexarch.dms.preparation.adapter.in.web;

import hexarch.dms.preparation.domain.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(Exception ex) {
        return ResponseEntity.status(422).body(ex.getMessage());
    }
}
