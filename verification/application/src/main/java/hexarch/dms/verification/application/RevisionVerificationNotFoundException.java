package hexarch.dms.verification.application;

import hexarch.dms.verification.domain.DocumentRevisionId;

public class RevisionVerificationNotFoundException extends RuntimeException {
    public RevisionVerificationNotFoundException(final DocumentRevisionId revisionId) {
        super("Revision verification for revision id %d not found".formatted(revisionId.getRevisionId()));
    }
}
