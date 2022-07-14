package hexarch.dms.preparation.adapter.out.verification;

import hexarch.dms.shared.domain.RevisionVerificationRequestedEvent;
import hexarch.dms.preparation.application.port.out.RequestVerificationPort;
import hexarch.dms.verification.application.port.in.PushRevisionToVerificationCommand;
import hexarch.dms.verification.application.port.in.PushRevisionToVerificationUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * An example of outbound adapter declared in configuration that orchestrates two use cases coming from different subdomains.
 */
@Service
@AllArgsConstructor
public class RequestVerificationAdapter implements RequestVerificationPort {

    private final PushRevisionToVerificationUseCase pushRevisionToVerificationUseCase;

    @Transactional
    @Override
    public void dispatch(RevisionVerificationRequestedEvent event) {
        System.out.printf("Verification requested for %d.%n", event.getRevisionId());
        pushRevisionToVerificationUseCase.apply(new PushRevisionToVerificationCommand(event.getRevisionId()));
    }
}
