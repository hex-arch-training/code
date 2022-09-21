package hexarch.dms.preparation.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExistingRevisionTest {

    private Revision revision;

    private Revision createRevision(RevisionStatus status) {
        return Revision.builder()
                .id((long) 1)
                .document(Document.builder().id(1L).title(new DocumentTitle("Test title")).build())
                .status(status)
                .content(new RevisionContent("Test content"))
                .build();
    }

    @Test
    public void shouldBeAbleToLockEditableRevision() {
        // given
        revision = createRevision(RevisionStatus.EDITABLE);
        // when
        revision.lock();
        // then
        assertEquals(RevisionStatus.LOCKED, revision.getStatus());
    }

    @Test
    public void shouldBeNotAbleToLockNonEditableRevision() {
        // given
        revision = createRevision(RevisionStatus.LOCKED);
        // then
        assertThrows(RevisionNotEditableException.class, () -> {
            // when
            revision.lock();
        });
    }
}
