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
class SaveRevisionAdapterTest {

    private static final DocumentTitle DOCUMENT_TITLE = new DocumentTitle("title");
    private static final RevisionContent REVISION_CONTENT = new RevisionContent("content");

    @Resource
    private RevisionRepository repository;

    private SaveRevisionAdapter adapter;

    @BeforeEach
    public void setUp() {
        adapter = new SaveRevisionAdapter(repository);
    }

    @Test
    void shouldSaveRevision() {
        // given
        final var revision = Revision.createNew(DOCUMENT_TITLE, REVISION_CONTENT);

        // when
        var id = adapter.save(revision);

        // then
        Optional<Revision> savedRevision = repository.findById(id);
        assertThat(savedRevision).isPresent();
        assertThat(savedRevision.get().getContent()).isEqualTo(REVISION_CONTENT);
        assertThat(savedRevision.get().getDocument().getTitle()).isEqualTo(DOCUMENT_TITLE);
    }
}
