package hexarch.dms.verification.adapter.out.preparation;

import hexarch.dms.preparation.application.port.RevisionQueryModel;
import hexarch.dms.preparation.application.port.in.QueryRevisionByIdUseCase;
import hexarch.dms.preparation.domain.DocumentTitle;
import hexarch.dms.preparation.domain.RevisionContent;
import hexarch.dms.verification.application.port.RevisionContentQueryModel;
import hexarch.dms.verification.domain.DocumentRevisionId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindRevisionContentAdapterTest {

    private static final DocumentRevisionId DOCUMENT_REVISION_ID = new DocumentRevisionId(44L);

    @Mock
    private QueryRevisionByIdUseCase queryRevisionByIdUseCase;

    @InjectMocks
    private FindRevisionContentAdapter findRevisionContentAdapter;

    @Test
    public void shouldFindRevisionPreview() {
        // given
        var title = new DocumentTitle("title");
        var content = new RevisionContent("content");
        var revisionQueryModel = new RevisionQueryModel(1l, 2l, title, content);
        when(queryRevisionByIdUseCase.queryBy(DOCUMENT_REVISION_ID.getRevisionId())).thenReturn(Optional.of(revisionQueryModel));

        // when
        Optional<RevisionContentQueryModel> revisionContentQueryModel = findRevisionContentAdapter.queryBy(DOCUMENT_REVISION_ID);

        // then
        assertThat(revisionContentQueryModel)
                .isPresent()
                .hasValueSatisfying(value -> {
                    assertThat(value.title()).isEqualTo(revisionQueryModel.documentTitle());
                    assertThat(value.content()).isEqualTo(revisionQueryModel.content());
                });
    }
}
