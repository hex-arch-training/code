package hexarch.dms.verification.application.port.in;

import hexarch.dms.verification.application.port.RevisionPreviewQueryModel;
import hexarch.dms.verification.domain.DocumentRevisionId;

import java.util.Optional;

public interface QueryRevisionPreviewUseCase {

    Optional<RevisionPreviewQueryModel> queryFor(DocumentRevisionId revisionId);
}
