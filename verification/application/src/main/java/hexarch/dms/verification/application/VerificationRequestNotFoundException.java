package hexarch.dms.verification.application;

import hexarch.dms.verification.domain.DocumentRevisionId;

public class VerificationRequestNotFoundException extends RuntimeException {
    public VerificationRequestNotFoundException(DocumentRevisionId revisionId) {
        super(String.format("Verification request for revision %d not found.", revisionId.getRevisionId()));
    }
}
