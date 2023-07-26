package hexarch.dms.preparation.application;

import hexarch.dms.preparation.application.port.in.RequestVerificationCommand;
import hexarch.dms.preparation.application.port.out.GetRevisionPort;
import hexarch.dms.preparation.application.port.out.RevisionVerificationRequestedEvent;
import hexarch.dms.preparation.application.port.out.PublishRevisionVerificationRequestedEventPort;
import hexarch.dms.preparation.application.port.out.SaveRevisionPort;
import hexarch.dms.preparation.domain.DocumentTitle;
import hexarch.dms.preparation.domain.Revision;
import hexarch.dms.preparation.domain.RevisionContent;
import hexarch.dms.preparation.domain.RevisionStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RequestVerificationServiceTest {

    private final Long ID = 11L;

    @Mock
    private GetRevisionPort getRevisionPort;

    @Mock
    private SaveRevisionPort saveRevisionPort;

    @Mock
    private PublishRevisionVerificationRequestedEventPort publishRevisionVerificationRequestedEventPort;

    @InjectMocks
    private RequestVerificationService requestVerificationService;

    @Test
    public void shouldThrowExceptionIfRevisionDoesNotExist() {
        // given
        when(getRevisionPort.getById(ID)).thenReturn(Optional.empty());
        // when-then
        var command = new RequestVerificationCommand(ID);
        assertThrows(RevisionNotFoundException.class, () -> {
            requestVerificationService.apply(command);
        });
    }

    @Test
    public void shouldSaveRevisionAndPublishEvent() {
        // given
        var revision = Revision.createExisting(ID, new DocumentTitle("title"), new RevisionContent("content"), RevisionStatus.EDITABLE);
        when(getRevisionPort.getById(ID)).thenReturn(Optional.of(revision));
        // when
        var command = new RequestVerificationCommand(ID);
        requestVerificationService.apply(command);
        // then
        assertEquals(RevisionStatus.LOCKED, revision.getStatus());
        verify(saveRevisionPort).saveRevision(revision);
        verify(publishRevisionVerificationRequestedEventPort).publish(eq(new RevisionVerificationRequestedEvent(ID)));
    }
}
