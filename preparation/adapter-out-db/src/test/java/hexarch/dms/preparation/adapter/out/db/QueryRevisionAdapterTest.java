package hexarch.dms.preparation.adapter.out.db;

import hexarch.dms.preparation.application.port.RevisionQueryModel;
import hexarch.dms.preparation.domain.DocumentTitle;
import hexarch.dms.preparation.domain.Revision;
import hexarch.dms.preparation.domain.RevisionContent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class QueryRevisionAdapterTest {

    private static final DocumentTitle DOCUMENT_TITLE = new DocumentTitle("foo");
    private static final RevisionContent REVISION_CONTENT = new RevisionContent("bar");
    @Resource
    private RevisionRepository revisionRepository;

    private QueryRevisionAdapter testedObject;

    @BeforeEach
    void setUp() {
        testedObject = new QueryRevisionAdapter(revisionRepository);
    }

    @Test
    void shouldQueryByIdAndFind() {
        // given
        var newRevision = Revision.createNew(DOCUMENT_TITLE, REVISION_CONTENT);
        var savedRevision = revisionRepository.save(newRevision);
        var revisionId = savedRevision.getId();

        // when
        var result = testedObject.queryBy(revisionId);

        // then
        var expectedModel = new RevisionQueryModel(revisionId, savedRevision.getDocument().getId(), DOCUMENT_TITLE, REVISION_CONTENT);
        assertTrue(result.isPresent());
        assertEquals(expectedModel, result.get());
    }

    @Test
    void shouldQueryByIdAndFindNothing() {
        // given
        var newRevision = Revision.createNew(DOCUMENT_TITLE, REVISION_CONTENT);
        var savedRevision = revisionRepository.save(newRevision);
        var revisionId = savedRevision.getId();

        // when
        var result = testedObject.queryBy(revisionId + 1);

        // then
        assertTrue(result.isEmpty());
    }
}