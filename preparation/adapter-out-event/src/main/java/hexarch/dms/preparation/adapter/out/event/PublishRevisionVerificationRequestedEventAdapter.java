package hexarch.dms.preparation.adapter.out.event;

import hexarch.dms.preparation.application.port.out.RevisionVerificationRequestedEvent;
import hexarch.dms.preparation.application.port.out.PublishRevisionVerificationRequestedEventPort;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * An example of outbound adapter that sends events.
 */
@Service
@AllArgsConstructor
public class PublishRevisionVerificationRequestedEventAdapter implements PublishRevisionVerificationRequestedEventPort {
    private final ApplicationEventPublisher applicationEventPublisher;


    @Override
    public void publish(RevisionVerificationRequestedEvent command) {
        applicationEventPublisher.publishEvent(new hexarch.dms.shared.event.RevisionVerificationRequestedEvent(this, command.getRevisionId()));
    }
}
