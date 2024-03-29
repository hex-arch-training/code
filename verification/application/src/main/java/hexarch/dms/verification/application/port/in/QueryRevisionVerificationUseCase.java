package hexarch.dms.verification.application.port.in;

import hexarch.dms.verification.application.port.VerificationRequestModel;
import hexarch.dms.verification.domain.DocumentRevisionId;

import java.util.Optional;

public interface QueryRevisionVerificationUseCase {
    Optional<VerificationRequestModel> queryBy(DocumentRevisionId revisionId);
}
