package hexarch.dms.verification.application;

import hexarch.dms.verification.domain.DocumentRevisionId;

public class VerificationRequestNotFoundException extends RuntimeException {
    public VerificationRequestNotFoundException(final DocumentRevisionId revisionId) {
        super("Verification request for revision %d not found.".formatted(revisionId.getRevisionId()));
    }
}
