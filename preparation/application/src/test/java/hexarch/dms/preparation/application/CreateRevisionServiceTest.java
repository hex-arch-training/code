package hexarch.dms.preparation.application;

import hexarch.dms.preparation.application.port.in.CreateRevisionCommand;
import hexarch.dms.preparation.application.port.out.SaveRevisionPort;
import hexarch.dms.preparation.domain.DocumentTitle;
import hexarch.dms.preparation.domain.Revision;
import hexarch.dms.preparation.domain.RevisionContent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateRevisionServiceTest {

    private static final String DOCUMENT_TITLE = "Document title";
    private static final String REVISION_CONTENT = "Revision content";
    private static final Long ID = 17L;

    private SaveRevisionPort saveRevisionPort;
    private CreateRevisionService createRevisionService;

    @BeforeEach
    public void beforeEach() {
        saveRevisionPort = Mockito.mock(SaveRevisionPort.class);
        createRevisionService = new CreateRevisionService(saveRevisionPort);
    }

    @Test
    public void shouldSaveRevisionViaPort() {
        // given
        var command = new CreateRevisionCommand(DOCUMENT_TITLE, REVISION_CONTENT);
        when(saveRevisionPort.saveRevision(any(Revision.class))).thenReturn(ID);
        // when
        var id = createRevisionService.apply(command);
        // then
        assertEquals(ID, id);
        verify(saveRevisionPort)
                .saveRevision(eq(Revision.createNew(
                        new DocumentTitle(DOCUMENT_TITLE),
                        new RevisionContent(REVISION_CONTENT))));
    }
}
