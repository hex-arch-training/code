package hexarch.dms.verification.application.port;

import hexarch.dms.verification.domain.DocumentRevisionId;
import hexarch.dms.verification.domain.VerificationStatus;

public record RevisionVerificationQueryModel(long revisionId, String verificationStatus) {
    public RevisionVerificationQueryModel(DocumentRevisionId revisionId, VerificationStatus verificationStatus) {
        this(revisionId.getRevisionId(), verificationStatus.name());
    }
}
