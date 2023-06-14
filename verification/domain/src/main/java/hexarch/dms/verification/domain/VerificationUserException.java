package hexarch.dms.verification.domain;

public class VerificationUserException extends RuntimeException {
    public VerificationUserException(User currentUser) {
        super("The user '%s' who created the revision verification cannot accept it".formatted(currentUser.getLogin()));
    }
}
