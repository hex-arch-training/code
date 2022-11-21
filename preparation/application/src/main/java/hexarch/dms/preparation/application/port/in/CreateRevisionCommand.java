package hexarch.dms.preparation.application.port.in;

import hexarch.dms.preparation.domain.DocumentTitle;
import hexarch.dms.preparation.domain.RevisionContent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class CreateRevisionCommand {
    private final DocumentTitle documentTitle;
    private final RevisionContent revisionContent;

    public CreateRevisionCommand(String documentTitle, String revisionContent) {
        this.documentTitle = new DocumentTitle(documentTitle);
        this.revisionContent = new RevisionContent(revisionContent);
    }
}
