package hexarch.dms.verification.adapter.in.event;

import hexarch.dms.shared.event.RevisionVerificationRequestedEvent;
import hexarch.dms.verification.application.port.in.PushRevisionToVerificationUseCase;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VerificationEventListener  implements ApplicationListener<RevisionVerificationRequestedEvent> {

    private final PushRevisionToVerificationUseCase pushRevisionToVerificationUseCase;

    @Override
    public void onApplicationEvent(final RevisionVerificationRequestedEvent event) {

    }
}
