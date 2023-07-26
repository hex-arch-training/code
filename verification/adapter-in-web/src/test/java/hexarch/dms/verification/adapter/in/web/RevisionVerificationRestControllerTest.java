package hexarch.dms.verification.adapter.in.web;

import hexarch.dms.verification.application.port.RevisionPreviewQueryModel;
import hexarch.dms.verification.application.port.in.AcceptRevisionCommand;
import hexarch.dms.verification.application.port.in.AcceptRevisionUseCase;
import hexarch.dms.verification.application.port.in.QueryRevisionPreviewUseCase;
import hexarch.dms.verification.domain.DocumentRevisionId;
import hexarch.dms.verification.domain.VerificationStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RevisionVerificationRestController.class)
class RevisionVerificationRestControllerTest {

    private static final Long REVISION_ID = 1L;

    @MockBean
    private AcceptRevisionUseCase acceptRevisionUseCase;

    @MockBean
    private QueryRevisionPreviewUseCase queryRevisionPreviewUseCase;

    @Autowired
    private MockMvc mvc;

    @Captor
    private ArgumentCaptor<AcceptRevisionCommand> acceptRevisionCommandCaptor;

    @Captor
    private ArgumentCaptor<DocumentRevisionId> documentRevisionIdCaptor;

    @Test
    void shouldCreateVerificationRequest() throws Exception {
        // given

        // when
        var resultActions = mvc.perform(post("/revision/{revisionId}/accept", REVISION_ID)
                .accept(APPLICATION_JSON_VALUE)
                .contentType(APPLICATION_JSON_VALUE));

        // then
        resultActions
                .andExpect(status().isOk());
        verify(acceptRevisionUseCase, times(1)).apply(acceptRevisionCommandCaptor.capture());
        assertThat(acceptRevisionCommandCaptor.getValue().revisionId().getRevisionId()).isEqualTo(REVISION_ID);
    }

    @Test
    void shouldReturnNoPreviewIfNotFound() throws Exception {
        // given

        // when
        var resultActions = mvc.perform(get("/revision/{revisionId}/preview", REVISION_ID)
                .contentType(APPLICATION_JSON_VALUE));

        // then
        resultActions
                .andExpect(status().isNotFound());
        verify(queryRevisionPreviewUseCase, times(1)).queryBy(documentRevisionIdCaptor.capture());
        assertThat(documentRevisionIdCaptor.getValue().getRevisionId()).isEqualTo(REVISION_ID);
    }

    @Test
    void shouldReturnPreview() throws Exception {
        // given
        String title = "title";
        String content = "content";
        var  documentRevisionId = new DocumentRevisionId(1);
        VerificationStatus verificationStatus = VerificationStatus.PENDING;

        var revisionPreviewQueryModel = Optional.of(
                new RevisionPreviewQueryModel(title, content, documentRevisionId, verificationStatus));

        when(queryRevisionPreviewUseCase.queryBy(any())).thenReturn(revisionPreviewQueryModel);

        // when
        var resultActions = mvc.perform(get("/revision/{revisionId}/preview", REVISION_ID)
                .contentType(APPLICATION_JSON_VALUE));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(title))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value(content))
                .andExpect(MockMvcResultMatchers.jsonPath("$.verificationStatus").value(verificationStatus.toString()));
        verify(queryRevisionPreviewUseCase, times(1)).queryBy(documentRevisionIdCaptor.capture());
        assertThat(documentRevisionIdCaptor.getValue().getRevisionId()).isEqualTo(REVISION_ID);
    }
}