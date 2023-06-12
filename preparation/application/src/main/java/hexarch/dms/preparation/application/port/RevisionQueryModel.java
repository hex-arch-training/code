package hexarch.dms.preparation.application.port;

import hexarch.dms.preparation.domain.DocumentTitle;
import hexarch.dms.preparation.domain.RevisionContent;

public record RevisionQueryModel(
        Long id,
        Long documentId,
        String documentTitle,
        String content) {

    public RevisionQueryModel(Long id, Long documentId, DocumentTitle documentTitle, RevisionContent revisionContent) {
        this(id, documentId, documentTitle.getValue(), revisionContent.getValue());
    }
}
