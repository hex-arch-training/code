package hexarch.dms.verification.adapter.in.web;

import hexarch.dms.verification.domain.VerificationStatusException;
import hexarch.dms.verification.domain.VerificationUserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class VerificationRestExceptionHandler {

    @ExceptionHandler({VerificationUserException.class, VerificationStatusException.class})
    public ResponseEntity<Object> handleVerificationUser(Exception ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }
}
