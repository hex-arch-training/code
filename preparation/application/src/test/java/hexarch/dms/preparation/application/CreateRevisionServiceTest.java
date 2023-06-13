package hexarch.dms.preparation.application;

import hexarch.dms.preparation.application.port.in.CreateRevisionUseCase;
import hexarch.dms.preparation.application.port.out.SaveRevisionPort;
import hexarch.dms.preparation.domain.DocumentTitle;
import hexarch.dms.preparation.domain.Revision;
import hexarch.dms.preparation.domain.RevisionContent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateRevisionServiceTest {

    private static final DocumentTitle DOCUMENT_TITLE = new DocumentTitle("Foo");
    private static final RevisionContent REVISION_CONTENT = new RevisionContent("Bar");

    private static final Long REVISION_ID = 21L;

    @Mock
    private SaveRevisionPort saveRevisionPort;

    @InjectMocks
    private CreateRevisionService testedObject;

    @Test
    void shouldSaveRevision() {
        // given
        var command = new CreateRevisionUseCase.CreateRevisionCommand(DOCUMENT_TITLE, REVISION_CONTENT);
        when(saveRevisionPort.save(any(Revision.class))).thenReturn(REVISION_ID);
        // when
        var id = testedObject.apply(command);
        // then
        assertEquals(REVISION_ID, id);
        verify(saveRevisionPort).save(eq(Revision.createNew(DOCUMENT_TITLE, REVISION_CONTENT)));
    }
}