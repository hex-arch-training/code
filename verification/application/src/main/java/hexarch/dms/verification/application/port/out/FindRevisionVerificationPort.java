package hexarch.dms.verification.application.port.out;

import hexarch.dms.verification.domain.DocumentRevisionId;
import hexarch.dms.verification.domain.RevisionVerification;

import java.util.Optional;

public interface FindRevisionVerificationPort {
    Optional<RevisionVerification> findByRevisionId(DocumentRevisionId revisionId);


}
