package hexarch.dms.verification.application;

import hexarch.dms.verification.application.port.VerificationRequestModel;
import hexarch.dms.verification.application.port.out.FindRevisionVerificationPort;
import hexarch.dms.verification.domain.DocumentRevisionId;
import hexarch.dms.verification.domain.RevisionVerification;
import hexarch.dms.verification.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QueryRevisionVerificationServiceTest {

    private static final DocumentRevisionId DOCUMENT_REVISION_ID = new DocumentRevisionId(44L);

    @Mock
    private FindRevisionVerificationPort findRevisionVerificationPort;

    @InjectMocks
    private QueryRevisionVerificationService queryRevisionVerificationService;

    @Test
    public void shouldFindRevisionVerification() {
        // given
        var user = new User("one");
        var revisionVerification = RevisionVerification.createNew(DOCUMENT_REVISION_ID, user);
        when(findRevisionVerificationPort.findByRevisionId(DOCUMENT_REVISION_ID)).thenReturn(Optional.of(revisionVerification));

        // when
        Optional<VerificationRequestModel> verificationRequestModel = queryRevisionVerificationService.queryBy(DOCUMENT_REVISION_ID);

        // then
        assertThat(verificationRequestModel)
                .isPresent()
                .hasValueSatisfying(value -> {
                    assertThat(value.documentRevisionId()).isEqualTo(revisionVerification.getDocumentRevisionId());
                    assertThat(value.verificationStatus()).isEqualTo(revisionVerification.getVerificationStatus());
                });
    }

}
