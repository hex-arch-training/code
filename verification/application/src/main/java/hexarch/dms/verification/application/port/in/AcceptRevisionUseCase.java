package hexarch.dms.verification.application.port.in;

import hexarch.dms.verification.domain.DocumentRevisionId;

public interface AcceptRevisionUseCase {

    void apply(AcceptRevisionCommand command);

    record AcceptRevisionCommand(DocumentRevisionId revisionId) {}
}
