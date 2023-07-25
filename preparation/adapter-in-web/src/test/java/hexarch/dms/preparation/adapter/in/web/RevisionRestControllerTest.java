package hexarch.dms.preparation.adapter.in.web;

import hexarch.dms.preparation.application.port.RevisionQueryModel;
import hexarch.dms.preparation.application.port.in.QueryRevisionByIdUseCase;
import hexarch.dms.preparation.domain.DocumentTitle;
import hexarch.dms.preparation.domain.RevisionContent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RevisionRestController.class)
class RevisionRestControllerTest {

    private static final Long DOCUMENT_ID = 1L;
    private static final Long REVISION_ID = 1L;
    private static DocumentTitle DOCUMENT_TITLE = new DocumentTitle("title");
    private static RevisionContent REVISION_CONTENT = new RevisionContent("content");

    @MockBean
    private QueryRevisionByIdUseCase queryRevisionByIdUseCase;

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldFindRevision() throws Exception {
        // given
        var revisionQueryModel = new RevisionQueryModel(REVISION_ID, DOCUMENT_ID, DOCUMENT_TITLE, REVISION_CONTENT);

        when(queryRevisionByIdUseCase.queryBy(anyLong())).thenReturn(Optional.of(revisionQueryModel));

        // when
        var resultActions = mvc.perform(get("/revision/{revisionId}", REVISION_ID)
                .accept(APPLICATION_JSON_VALUE)
                .contentType(APPLICATION_JSON_VALUE));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(REVISION_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$.documentId").value(DOCUMENT_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$.documentTitle").value(DOCUMENT_TITLE.getValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value(REVISION_CONTENT.getValue()));
        verify(queryRevisionByIdUseCase, times(1)).queryBy(REVISION_ID);
    }

    @Test
    void shouldNotFindRevision() throws Exception {
        // given
        when(queryRevisionByIdUseCase.queryBy(anyLong())).thenReturn(Optional.empty());

        // when
        var resultActions = mvc.perform(get("/revision/{revisionId}", REVISION_ID)
                .accept(APPLICATION_JSON_VALUE)
                .contentType(APPLICATION_JSON_VALUE));

        // then
        resultActions.andExpect(status().isNotFound());
        verify(queryRevisionByIdUseCase, times(1)).queryBy(REVISION_ID);
    }
}