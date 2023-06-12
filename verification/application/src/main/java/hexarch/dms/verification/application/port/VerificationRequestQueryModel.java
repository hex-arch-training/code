package hexarch.dms.verification.application.port;

import hexarch.dms.verification.domain.DocumentRevisionId;
import hexarch.dms.verification.domain.VerificationStatus;

public record VerificationRequestQueryModel(long revisionId, String verificationStatus) {

    public VerificationRequestQueryModel(DocumentRevisionId revisionId, VerificationStatus verificationStatus) {
        this(revisionId.getRevisionId(), verificationStatus.name());
    }
}
