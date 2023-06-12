package hexarch.dms.preparation.adapter.out.event;

import hexarch.dms.preparation.application.port.out.PublishRevisionVerificationRequestedEventPort;
import hexarch.dms.shared.event.RevisionVerificationRequestedEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class PublishRevisionVerificationRequestedEventAdapter implements PublishRevisionVerificationRequestedEventPort {

    private final ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void publish(final long revisionId) {
        applicationEventPublisher.publishEvent(new RevisionVerificationRequestedEvent(this, revisionId));
    }
}
