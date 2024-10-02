package hexarch.dms.verification.application.port.in;

import hexarch.dms.verification.application.port.RevisionPreviewQueryModel;
import hexarch.dms.verification.domain.DocumentRevisionId;

public interface QueryRevisionPreviewUseCase {
    RevisionPreviewQueryModel queryBy(DocumentRevisionId revisionId);
}
