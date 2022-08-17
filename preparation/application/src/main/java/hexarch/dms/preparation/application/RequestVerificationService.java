package hexarch.dms.preparation.application;

import hexarch.dms.preparation.application.port.in.RequestVerificationCommand;
import hexarch.dms.preparation.application.port.in.RequestVerificationUseCase;
import hexarch.dms.preparation.application.port.out.GetRevisionPort;
import hexarch.dms.shared.domain.RevisionVerificationRequestedEvent;
import hexarch.dms.preparation.application.port.out.RequestVerificationPort;
import hexarch.dms.preparation.application.port.out.SaveRevisionPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RequestVerificationService implements RequestVerificationUseCase {
    private final GetRevisionPort getRevisionPort;
    private final SaveRevisionPort saveRevisionPort;
    private final RequestVerificationPort requestVerificationPort;

    @Transactional
    @Override
    public void apply(RequestVerificationCommand command) {
        var revision = getRevisionPort.getById(command.getRevisionId())
                .orElseThrow(() -> new RevisionNotFoundException(command.getRevisionId()));
        revision.lock();
        saveRevisionPort.saveRevision(revision);
        requestVerificationPort.dispatch(new RevisionVerificationRequestedEvent(revision.getId()));
    }
}
