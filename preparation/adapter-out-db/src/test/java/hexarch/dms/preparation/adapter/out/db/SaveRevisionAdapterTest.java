package hexarch.dms.preparation.adapter.out.db;

import hexarch.dms.preparation.domain.DocumentTitle;
import hexarch.dms.preparation.domain.Revision;
import hexarch.dms.preparation.domain.RevisionContent;
import hexarch.dms.preparation.domain.RevisionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SaveRevisionAdapterTest {

    private static final DocumentTitle DOCUMENT_TITLE = new DocumentTitle("foo");
    private static final RevisionContent REVISION_CONTENT = new RevisionContent("bar");

    @Resource
    private RevisionRepository revisionRepository;

    private SaveRevisionAdapter testedObject;

    @BeforeEach
    void setUp() {
        testedObject = new SaveRevisionAdapter(revisionRepository);
    }

    @Test
    void shouldSaveRevision() {
        // given
        var newRevision = Revision.createNew(DOCUMENT_TITLE, REVISION_CONTENT);
        // when
        var id = testedObject.save(newRevision);
        // then
        var savedRevision = revisionRepository.findById(id);
        assertTrue(savedRevision.isPresent());
        savedRevision.ifPresent(revision -> {
            assertEquals(DOCUMENT_TITLE, revision.getDocument().getTitle());
            assertEquals(REVISION_CONTENT, revision.getContent());
            assertEquals(id, revision.getId());
            assertEquals(RevisionStatus.EDITABLE, revision.getStatus());
        });
    }
}