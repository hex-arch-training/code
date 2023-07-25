package hexarch.dms.verification.adapter.in.event;

import hexarch.dms.shared.event.RevisionVerificationRequestedEvent;
import hexarch.dms.verification.application.port.in.PushRevisionToVerificationCommand;
import hexarch.dms.verification.application.port.in.PushRevisionToVerificationUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VerificationEventListenerTest {

    @Mock
    private PushRevisionToVerificationUseCase pushRevisionToVerificationUseCase;

    @InjectMocks
    private VerificationEventListener verificationEventListener;

    @Test
    void shouldPushRevisionToVerification() {
        // given
        long revisionId = 4l;
        var event = new RevisionVerificationRequestedEvent(this, revisionId);

        // when
        verificationEventListener.onApplicationEvent(event);

        // then
        verify(pushRevisionToVerificationUseCase).apply(eq(new PushRevisionToVerificationCommand(revisionId)));
    }
}