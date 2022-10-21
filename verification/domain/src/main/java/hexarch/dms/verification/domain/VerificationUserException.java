package hexarch.dms.verification.domain;

public class VerificationUserException extends RuntimeException {
    public VerificationUserException(User currentUser) {
        super(String.format("The user '%s' who created the revision verification cannot accept it", currentUser.getLogin()));
    }
}
