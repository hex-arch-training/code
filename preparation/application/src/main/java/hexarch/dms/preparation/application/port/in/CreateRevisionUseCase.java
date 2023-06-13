package hexarch.dms.preparation.application.port.in;

import hexarch.dms.preparation.domain.DocumentTitle;
import hexarch.dms.preparation.domain.RevisionContent;

public interface CreateRevisionUseCase {

    long apply(CreateRevisionCommand command);


    record CreateRevisionCommand(DocumentTitle documentTitle, RevisionContent revisionContent) {}
}
