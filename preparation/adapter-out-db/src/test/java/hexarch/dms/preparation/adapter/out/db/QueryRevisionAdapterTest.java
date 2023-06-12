package hexarch.dms.preparation.adapter.out.db;

import hexarch.dms.preparation.application.port.RevisionQueryModel;
import hexarch.dms.preparation.domain.DocumentTitle;
import hexarch.dms.preparation.domain.Revision;
import hexarch.dms.preparation.domain.RevisionContent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.annotation.Resource;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class QueryRevisionAdapterTest {
    private static final DocumentTitle DOCUMENT_TITLE = new DocumentTitle("title");
    private static final RevisionContent REVISION_CONTENT = new RevisionContent("content");

    @Resource
    private RevisionRepository repository;

    private QueryRevisionAdapter adapter;

    @BeforeEach
    public void setUp() {
        adapter = new QueryRevisionAdapter(repository);
    }

    @Test
    void shouldQueryByIdAndFind() {
        // given
        var newRevision = Revision.createNew(DOCUMENT_TITLE, REVISION_CONTENT);
        Revision savedRevision = repository.save(newRevision);
        var revisionId = savedRevision.getId();
        var expectedRevisionQueryModel = new RevisionQueryModel(savedRevision.getId(), savedRevision.getDocument().getId(), DOCUMENT_TITLE, REVISION_CONTENT);

        // when
        Optional<RevisionQueryModel> revisionQueryModel = adapter.queryBy(revisionId);

        // then
        assertThat(revisionQueryModel).hasValue(expectedRevisionQueryModel);
    }

    @Test
    void shouldQueryByIdAndFindNothingIfNoData() {
        // given
        var revisionId = 1L;
        // no data

        // when
        Optional<RevisionQueryModel> revisionQueryModel = adapter.queryBy(revisionId);

        // then
        assertThat(revisionQueryModel).isNotPresent();
    }

    @Test
    void shouldQueryByIdAndFindNothingIfOtherData() {
        // given
        var newRevision = Revision.createNew(DOCUMENT_TITLE, REVISION_CONTENT);
        Revision savedRevision = repository.save(newRevision);
        var otherRevisionId = savedRevision.getId();
        var revisionId = otherRevisionId + 1;

        // when
        Optional<RevisionQueryModel> revisionQueryModel = adapter.queryBy(revisionId);

        // then
        assertThat(revisionQueryModel).isNotPresent();
    }


}