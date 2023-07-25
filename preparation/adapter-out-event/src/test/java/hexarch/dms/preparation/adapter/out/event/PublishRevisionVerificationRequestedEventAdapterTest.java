package hexarch.dms.preparation.adapter.out.event;

import hexarch.dms.preparation.application.port.out.RevisionVerificationRequestedEvent;
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
class PublishRevisionVerificationRequestedEventAdapterTest {

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    private PublishRevisionVerificationRequestedEventAdapter publishRevisionVerificationRequestedEventAdapter;

    @Captor
    private ArgumentCaptor<hexarch.dms.shared.event.RevisionVerificationRequestedEvent> revisionVerificationRequestedEventCaptor;

    @Test
    void shouldPushRevisionToVerificationAdapter() {
        // given
        var revisionId = 1L;
        var event = new RevisionVerificationRequestedEvent(revisionId);

        // when
        publishRevisionVerificationRequestedEventAdapter.publish(event);

        // then
        verify(applicationEventPublisher).publishEvent(revisionVerificationRequestedEventCaptor.capture());
        assertThat(revisionVerificationRequestedEventCaptor.getValue().getRevisionId()).isEqualTo(revisionId);
        assertThat(revisionVerificationRequestedEventCaptor.getValue().getSource()).isEqualTo(publishRevisionVerificationRequestedEventAdapter);
    }
}