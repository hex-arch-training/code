package hexarch.dms.preparation.application;

import hexarch.dms.preparation.application.port.in.RequestVerificationCommand;
import hexarch.dms.preparation.application.port.out.GetRevisionPort;
import hexarch.dms.preparation.application.port.out.PushRevisionToVerificationCommand;
import hexarch.dms.preparation.application.port.out.PushRevisionToVerificationPort;
import hexarch.dms.preparation.application.port.out.SaveRevisionPort;
import hexarch.dms.preparation.domain.DocumentTitle;
import hexarch.dms.preparation.domain.Revision;
import hexarch.dms.preparation.domain.RevisionContent;
import hexarch.dms.preparation.domain.RevisionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RequestVerificationServiceTest {

    private final Long ID = 11L;

    private RequestVerificationService requestVerificationService;
    private GetRevisionPort getRevisionPort;
    private SaveRevisionPort saveRevisionPort;
    private PushRevisionToVerificationPort pushRevisionToVerificationPort;

    @BeforeEach
    public void beforeEach() {
        getRevisionPort = Mockito.mock(GetRevisionPort.class);
        saveRevisionPort = Mockito.mock(SaveRevisionPort.class);
        pushRevisionToVerificationPort = Mockito.mock(PushRevisionToVerificationPort.class);
        requestVerificationService = new RequestVerificationService(getRevisionPort, saveRevisionPort, pushRevisionToVerificationPort);
    }

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
        verify(pushRevisionToVerificationPort).apply(eq(new PushRevisionToVerificationCommand(ID)));
    }
}
