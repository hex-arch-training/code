package hexarch.dms.preparation.application;

import hexarch.dms.preparation.application.port.in.CreateRevisionUseCase;
import hexarch.dms.preparation.application.port.out.SaveRevisionPort;
import hexarch.dms.preparation.domain.Revision;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class CreateRevisionService implements CreateRevisionUseCase {

    private final SaveRevisionPort saveRevisionPort;

    @Override
    public long apply(@NonNull final CreateRevisionCommand command) {
        var newRevision = Revision.createNew(command.documentTitle(), command.revisionContent());
        return saveRevisionPort.save(newRevision);
    }
}
