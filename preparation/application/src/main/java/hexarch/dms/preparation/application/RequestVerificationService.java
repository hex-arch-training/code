package hexarch.dms.preparation.application;

import hexarch.dms.preparation.application.port.in.RequestVerificationCommand;
import hexarch.dms.preparation.application.port.in.RequestVerificationUseCase;
import hexarch.dms.preparation.application.port.out.GetRevisionPort;
import hexarch.dms.preparation.application.port.out.RevisionVerificationRequestedEvent;
import hexarch.dms.preparation.application.port.out.PublishRevisionVerificationRequestedEventPort;
import hexarch.dms.preparation.application.port.out.SaveRevisionPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RequestVerificationService implements RequestVerificationUseCase {
    private final GetRevisionPort getRevisionPort;
    private final SaveRevisionPort saveRevisionPort;
    private final PublishRevisionVerificationRequestedEventPort publishVerificationRequestedEventPort;

    @Transactional
    @Override
    public void apply(RequestVerificationCommand command) {
        var revision = getRevisionPort.getById(command.revisionId())
                .orElseThrow(() -> new RevisionNotFoundException(command.revisionId()));
        revision.lock();
        saveRevisionPort.saveRevision(revision);
        publishVerificationRequestedEventPort.publish(new RevisionVerificationRequestedEvent(revision.getId()));
    }
}
