package hexarch.dms.verification.adapter.in.event;

import hexarch.dms.shared.event.RevisionVerificationRequestedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * An example of inbound adapter that handles events coming from different subdomains.
 */
@Service
public class VerificationEventListener implements ApplicationListener<RevisionVerificationRequestedEvent> {
    @Override
    public void onApplicationEvent(RevisionVerificationRequestedEvent event) {

    }
}
