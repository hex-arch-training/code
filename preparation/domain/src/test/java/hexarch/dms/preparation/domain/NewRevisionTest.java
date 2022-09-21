package hexarch.dms.preparation.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewRevisionTest {

    private static final String TEST_DOCUMENT_TITLE = "Test document";
    private static final String TEST_REVISION_CONTENT = "Some sample content";

    private Revision revision;

    @BeforeEach
    public void beforeEach() {
        revision = Revision.createNew(new DocumentTitle(TEST_DOCUMENT_TITLE), new RevisionContent(TEST_REVISION_CONTENT));
    }

    @Test
    public void shouldHaveDocument() {
        // then
        assertNotNull(revision.getDocument());
        assertEquals(TEST_DOCUMENT_TITLE, revision.getDocument().getTitle().getValue());
    }

    @Test
    public void shouldHaveContent() {
        // then
        assertEquals(TEST_REVISION_CONTENT, revision.getContent().getValue());
    }

    @Test
    public void shouldBeEditable() {
        // then
        assertEquals(RevisionStatus.EDITABLE, revision.getStatus());
    }

    @Test
    public void shouldNewRevisionBeLockable() {
        // when
        revision.lock();
        // then
        assertEquals(RevisionStatus.LOCKED, revision.getStatus());
    }

    @Test()
    public void shouldBeUnableToLockAlreadyLockedRevision() {
        // given
        revision.lock();
        // when
        assertThrows(RevisionNotEditableException.class, () -> {
            revision.lock();
        });
    }
}
