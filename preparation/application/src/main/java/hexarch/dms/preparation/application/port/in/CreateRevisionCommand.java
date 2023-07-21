package hexarch.dms.preparation.application.port.in;

import hexarch.dms.preparation.domain.DocumentTitle;
import hexarch.dms.preparation.domain.RevisionContent;

public record CreateRevisionCommand(DocumentTitle documentTitle, RevisionContent revisionContent) {

    public CreateRevisionCommand(String documentTitle, String revisionContent) {
        this(new DocumentTitle(documentTitle), new RevisionContent(revisionContent));
    }

}
