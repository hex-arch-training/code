package hexarch.dms.verification.application.port.in;

import hexarch.dms.verification.application.port.VerificationRequestQueryModel;
import hexarch.dms.verification.domain.DocumentRevisionId;

import java.util.Optional;

public interface QueryRevisionVerificationUseCase {
    Optional<VerificationRequestQueryModel> queryBy(DocumentRevisionId revisionId);
}
