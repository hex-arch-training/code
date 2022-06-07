package hexarch.dms.preparation.domain;

public class ValidationException extends RuntimeException {
    public ValidationException(String reason) {
        super(reason);
    }
}
