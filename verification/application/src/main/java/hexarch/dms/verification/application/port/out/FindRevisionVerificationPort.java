package hexarch.dms.verification.application.port.out;

import hexarch.dms.verification.application.port.RevisionVerificationQueryModel;
import hexarch.dms.verification.domain.DocumentRevisionId;

import java.util.Optional;

public interface FindRevisionVerificationPort {
    Optional<RevisionVerificationQueryModel> findBy(DocumentRevisionId revisionId);
}
