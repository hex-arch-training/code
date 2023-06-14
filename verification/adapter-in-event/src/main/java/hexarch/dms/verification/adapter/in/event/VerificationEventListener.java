package hexarch.dms.verification.adapter.in.event;

import hexarch.dms.shared.event.RevisionVerificationRequestedEvent;
import hexarch.dms.verification.application.port.in.PushRevisionToVerificationUseCase;
import hexarch.dms.verification.domain.DocumentRevisionId;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class VerificationEventListener implements ApplicationListener<RevisionVerificationRequestedEvent> {

    private final PushRevisionToVerificationUseCase pushRevisionToVerificationUseCase;

    @Override
    public void onApplicationEvent(final RevisionVerificationRequestedEvent event) {
        pushRevisionToVerificationUseCase.apply(
                new PushRevisionToVerificationUseCase.PushRevisionToVerificationCommand(
                        new DocumentRevisionId(event.getRevisionId())));
    }
}
