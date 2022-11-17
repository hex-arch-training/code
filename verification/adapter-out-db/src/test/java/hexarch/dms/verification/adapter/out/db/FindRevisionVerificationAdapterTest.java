package hexarch.dms.verification.adapter.out.db;

import hexarch.dms.verification.domain.DocumentRevisionId;
import hexarch.dms.verification.domain.RevisionVerification;
import hexarch.dms.verification.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.annotation.Resource;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class FindRevisionVerificationAdapterTest {

    private static final DocumentRevisionId DOCUMENT_REVISION_ID = new DocumentRevisionId(44L);

    @Resource
    private VerificationRequestRepository repository;

    private FindRevisionVerificationAdapter adapter;

    @BeforeEach
    public void setUp() {
        adapter = new FindRevisionVerificationAdapter(repository);
    }

    @Test
    void shouldFind() {
        // given
        var user = new User("one");
        var revisionVerification = RevisionVerification.createNew(DOCUMENT_REVISION_ID, user);
        repository.save(revisionVerification);

        // when
        Optional<RevisionVerification> foundRevisionVerification = adapter.findByRevisionId(DOCUMENT_REVISION_ID);

        // then
        assertThat(foundRevisionVerification)
                .isPresent()
                .hasValueSatisfying(value -> {
                    assertThat(value.getDocumentRevisionId()).isEqualTo(revisionVerification.getDocumentRevisionId());
                    assertThat(value.getVerificationStatus()).isEqualTo(revisionVerification.getVerificationStatus());
                });
    }


    @Test
    void shouldNotFind() {
        // given
        // nothing

        // when
        Optional<RevisionVerification> foundRevisionVerification = adapter.findByRevisionId(DOCUMENT_REVISION_ID);

        // then
        assertThat(foundRevisionVerification)
                .isNotPresent();
    }
}
