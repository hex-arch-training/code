package hexarch.dms.preparation.application.port;

import hexarch.dms.preparation.domain.DocumentTitle;
import hexarch.dms.preparation.domain.RevisionContent;

public record RevisionQueryModel(Long id, Long documentId, String documentTitle, String content) {
    public RevisionQueryModel(Long id, Long documentId, DocumentTitle documentTitle, RevisionContent content) {
        this(id, documentId, documentTitle.getValue(), content.getValue());
    }
}
