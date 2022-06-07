package hexarch.dms.preparation.application.port;

import hexarch.dms.preparation.domain.DocumentTitle;
import hexarch.dms.preparation.domain.RevisionContent;
import lombok.Value;

@Value
public class RevisionQueryModel {
    Long id;
    Long documentId;
    String documentTitle;
    String content;

    public RevisionQueryModel(Long id, Long documentId, DocumentTitle documentTitle, RevisionContent content) {
        this.id = id;
        this.documentId = documentId;
        this.documentTitle = documentTitle.getValue();
        this.content = content.getValue();
    }
}
