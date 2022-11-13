package hexarch.dms.preparation.application;

import hexarch.dms.preparation.application.port.in.CreateRevisionCommand;
import hexarch.dms.preparation.application.port.in.CreateRevisionUseCase;
import hexarch.dms.preparation.application.port.out.SaveRevisionPort;
import hexarch.dms.preparation.domain.Revision;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // we allow using Spring annotations to create services
@AllArgsConstructor
// watch the package private access!
class CreateRevisionService implements CreateRevisionUseCase {
    private final SaveRevisionPort saveRevisionPort;

    @Transactional // we allow using Spring annotations to set transaction boundaries
    @Override
    public Long apply(@NonNull CreateRevisionCommand command) {
        final var newRevision = Revision.createNew(
                command.getDocumentTitle(), command.getRevisionContent());
        return saveRevisionPort.saveRevision(newRevision);
    }
}
