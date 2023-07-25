package hexarch.dms.preparation.adapter.out.db;

import hexarch.dms.preparation.domain.DocumentTitle;
import hexarch.dms.preparation.domain.Revision;
import hexarch.dms.preparation.domain.RevisionContent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.annotation.Resource;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class GetRevisionAdapterTest {

    private static DocumentTitle DOCUMENT_TITLE = new DocumentTitle("title");
    private static RevisionContent REVISION_CONTENT = new RevisionContent("content");

    @Resource
    private RevisionRepository repository;

    private GetRevisionAdapter adapter;

    @BeforeEach
    public void setUp() {
        adapter = new GetRevisionAdapter(repository);
    }


    @Test
    void shouldGetByIdAndFind() {
        // given
        var newRevision = Revision.createNew(DOCUMENT_TITLE, REVISION_CONTENT);
        Revision savedRevision = repository.save(newRevision);
        var revisionId = savedRevision.getId();

        // when
        Optional<Revision> revision = adapter.getById(revisionId);

        // then
        assertThat(revision).isPresent();
    }

    @Test
    void shouldGetByIdAndFindNothingIfNoData() {
        // given
        var revisionId = 1L;
        // no data

        // when
        Optional<Revision> revision = adapter.getById(revisionId);

        // then
        assertThat(revision).isNotPresent();
    }

    @Test
    void shouldGetByIdAndFindNothingIfOtherData() {
        // given
        var newRevision = Revision.createNew(DOCUMENT_TITLE, REVISION_CONTENT);
        Revision savedRevision = repository.save(newRevision);
        var otherRevisionId = savedRevision.getId();
        var revisionId = otherRevisionId + 1;

        // when
        Optional<Revision> revision = adapter.getById(revisionId);

        // then
        assertThat(revision).isNotPresent();
    }

}