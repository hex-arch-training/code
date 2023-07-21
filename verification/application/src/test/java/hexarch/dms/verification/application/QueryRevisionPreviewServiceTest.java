package hexarch.dms.verification.application;

import hexarch.dms.verification.application.port.RevisionContentQueryModel;
import hexarch.dms.verification.application.port.RevisionPreviewQueryModel;
import hexarch.dms.verification.application.port.out.FindRevisionContentPort;
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
class QueryRevisionPreviewServiceTest {

    private static final DocumentRevisionId DOCUMENT_REVISION_ID = new DocumentRevisionId(44L);

    @Mock
    private FindRevisionVerificationPort findRevisionVerificationPort;

    @Mock
    private FindRevisionContentPort findRevisionContentPort;

    @InjectMocks
    private QueryRevisionPreviewService queryRevisionPreviewService;

    @Test
    public void shouldFindRevisionPreview() {
        // given
        var user = new User("one");
        var revisionVerification = RevisionVerification.createNew(DOCUMENT_REVISION_ID, user);
        when(findRevisionVerificationPort.findByRevisionId(DOCUMENT_REVISION_ID)).thenReturn(Optional.of(revisionVerification));
        var title = "title";
        var content = "content";
        var revisionContentQueryModel = new RevisionContentQueryModel(title, content);
        when(findRevisionContentPort.queryBy(DOCUMENT_REVISION_ID)).thenReturn(Optional.of(revisionContentQueryModel));

        // when
        Optional<RevisionPreviewQueryModel> revisionPreviewQueryModel = queryRevisionPreviewService.queryBy(DOCUMENT_REVISION_ID);

        // then
        assertThat(revisionPreviewQueryModel)
                .isPresent()
                .hasValueSatisfying(value -> {
                    assertThat(value.documentRevisionId()).isEqualTo(revisionVerification.getDocumentRevisionId());
                    assertThat(value.verificationStatus()).isEqualTo(revisionVerification.getVerificationStatus());
                    assertThat(value.title()).isEqualTo(revisionContentQueryModel.title());
                    assertThat(value.content()).isEqualTo(revisionContentQueryModel.content());
                });

    }
}
