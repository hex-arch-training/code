package hexarch.dms.verification.application.port.in;

import java.util.Optional;

import hexarch.dms.verification.application.port.VerificationRequestModel;
import hexarch.dms.verification.domain.DocumentRevisionId;

public interface QueryRevisionVerificationUseCase {
    Optional<VerificationRequestModel> queryBy(DocumentRevisionId revisionId);
}
