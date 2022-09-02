package hexarch.dms.verification.adapter.in.event;

import hexarch.dms.shared.event.RevisionVerificationRequestedEvent;
import hexarch.dms.verification.application.port.in.PushRevisionToVerificationCommand;
import hexarch.dms.verification.application.port.in.PushRevisionToVerificationUseCase;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * An example of inbound adapter that handles events coming from different subdomains.
 */
@Service
@AllArgsConstructor
public class VerificationEventListener implements ApplicationListener<RevisionVerificationRequestedEvent> {

    private final PushRevisionToVerificationUseCase pushRevisionToVerificationUseCase;

    @Override
    public void onApplicationEvent(RevisionVerificationRequestedEvent event) {
        System.out.printf("Verification requested for %d.%n", event.getRevisionId());
        pushRevisionToVerificationUseCase.apply(new PushRevisionToVerificationCommand(event.getRevisionId()));
    }
}
