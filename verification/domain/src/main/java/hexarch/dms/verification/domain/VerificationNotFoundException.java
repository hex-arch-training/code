package hexarch.dms.verification.domain;

public class VerificationNotFoundException extends RuntimeException {
    public VerificationNotFoundException(DocumentRevisionId revisionId) {
        super("Verification request for document revision %d not found".formatted(revisionId.getRevisionId()));
    }
}
