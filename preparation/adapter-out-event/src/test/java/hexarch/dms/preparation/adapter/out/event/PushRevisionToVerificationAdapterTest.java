package hexarch.dms.preparation.adapter.out.event;

import hexarch.dms.preparation.application.port.out.PushRevisionToVerificationCommand;
import hexarch.dms.shared.event.RevisionVerificationRequestedEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PushRevisionToVerificationAdapterTest {

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    private PushRevisionToVerificationAdapter pushRevisionToVerificationAdapter;

    @Captor
    private ArgumentCaptor<RevisionVerificationRequestedEvent> revisionVerificationRequestedEventCaptor;

    @Test
    void shouldPushRevisionToVerificationAdapter() {
        // given
        var revisionId = 1L;
        var command = new PushRevisionToVerificationCommand(revisionId);

        // when
        pushRevisionToVerificationAdapter.apply(command);

        // then
        verify(applicationEventPublisher).publishEvent(revisionVerificationRequestedEventCaptor.capture());
        assertThat(revisionVerificationRequestedEventCaptor.getValue().getRevisionId()).isEqualTo(revisionId);
        assertThat(revisionVerificationRequestedEventCaptor.getValue().getSource()).isEqualTo(pushRevisionToVerificationAdapter);
    }
}
