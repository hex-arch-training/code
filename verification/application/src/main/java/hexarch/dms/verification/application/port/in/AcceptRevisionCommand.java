package hexarch.dms.verification.application.port.in;

import hexarch.dms.verification.domain.DocumentRevisionId;

public record AcceptRevisionCommand(DocumentRevisionId revisionId) {
}
