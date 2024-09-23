package hexarch.dms.verification.adapter.out.db;

import hexarch.dms.verification.domain.DocumentRevisionId;
import hexarch.dms.verification.domain.RevisionVerification;
import hexarch.dms.verification.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import jakarta.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SaveRevisionVerificationAdapterTest {

    private static final DocumentRevisionId DOCUMENT_REVISION_ID = new DocumentRevisionId(44L);

    @Resource
    private VerificationRequestRepository repository;

    private SaveRevisionVerificationAdapter adapter;

    @BeforeEach
    public void setUp() {
        adapter = new SaveRevisionVerificationAdapter(repository);
    }

    @Test
    void shouldSaveRevision() {
        // given
        var user = new User("one");
        var revisionVerification = RevisionVerification.createNew(DOCUMENT_REVISION_ID, user);

        // when
        var id = adapter.save(revisionVerification);

        // then
        assertThat(repository.findById(id))
                .isPresent()
                .hasValueSatisfying(value -> {
                    assertThat(value.getDocumentRevisionId()).isEqualTo(revisionVerification.getDocumentRevisionId());
                    assertThat(value.getVerificationStatus()).isEqualTo(revisionVerification.getVerificationStatus());
                });
    }
}
