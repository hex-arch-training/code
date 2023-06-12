package hexarch.dms.verification.application.port.in;

import hexarch.dms.verification.domain.DocumentRevisionId;

public interface PushRevisionToVerificationUseCase {

    void apply(PushRevisionToVerificationCommand command);

    record PushRevisionToVerificationCommand(DocumentRevisionId revisionId) {}
}
