package hexarch.dms.preparation.adapter.out.event;

import hexarch.dms.preparation.application.port.out.PushRevisionToVerificationCommand;
import hexarch.dms.preparation.application.port.out.PushRevisionToVerificationPort;
import hexarch.dms.shared.event.RevisionVerificationRequestedEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * An example of outbound adapter that sends events to different subdomains.
 */
@Service
@AllArgsConstructor
public class PushRevisionToVerificationAdapter implements PushRevisionToVerificationPort {
    private final ApplicationEventPublisher applicationEventPublisher;


    @Override
    public void apply(PushRevisionToVerificationCommand command) {
        applicationEventPublisher.publishEvent(new RevisionVerificationRequestedEvent(this, command.getRevisionId()));
    }
}
