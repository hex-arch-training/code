package hexarch.dms.preparation.application;

import hexarch.dms.preparation.application.port.in.RequestVerificationUseCase;
import hexarch.dms.preparation.application.port.out.GetRevisionPort;
import hexarch.dms.preparation.application.port.out.PublishRevisionVerificationRequestedEventPort;
import hexarch.dms.preparation.application.port.out.SaveRevisionPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
class RequestVerificationService implements RequestVerificationUseCase {

    private final GetRevisionPort getRevisionPort;

    private final SaveRevisionPort saveRevisionPort;

    private final PublishRevisionVerificationRequestedEventPort publishRevisionVerificationRequestedEventPort;

    @Transactional
    @Override
    public void apply(final RequestVerificationCommand command) {
        var revision = getRevisionPort.getById(command.revisionId()).orElseThrow(() -> new RevisionNotFoundException(command.revisionId()));
        revision.lock();
        saveRevisionPort.save(revision);
        publishRevisionVerificationRequestedEventPort.publish(revision.getId());
    }
}
