package hexarch.dms.verification.application.port.out;

import hexarch.dms.verification.application.port.RevisionContentQueryModel;
import hexarch.dms.verification.domain.DocumentRevisionId;

import java.util.Optional;

public interface FindRevisionContentPort {

    Optional<RevisionContentQueryModel> queryBy(DocumentRevisionId revisionId);
}
