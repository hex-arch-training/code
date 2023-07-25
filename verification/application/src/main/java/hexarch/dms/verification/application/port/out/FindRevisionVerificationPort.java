package hexarch.dms.verification.application.port.out;

import java.util.Optional;

import hexarch.dms.verification.domain.DocumentRevisionId;
import hexarch.dms.verification.domain.RevisionVerification;

public interface FindRevisionVerificationPort {
    Optional<RevisionVerification> findByRevisionId(DocumentRevisionId revisionId);
}
