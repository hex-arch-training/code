package hexarch.dms.preparation.application.port.in;

import hexarch.dms.preparation.domain.DocumentTitle;
import hexarch.dms.preparation.domain.RevisionContent;

public record RevisionQueryModel(
        long id,
        long documentId,
        String documentTitle,
        String revisionContent
) {
    public RevisionQueryModel(long id, long documentId, DocumentTitle documentTitle, RevisionContent revisionContent) {
        this(id, documentId, documentTitle.getValue(), revisionContent.getValue());
    }
}
