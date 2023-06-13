package hexarch.dms.preparation.adapter.in.web;

import hexarch.dms.preparation.application.port.in.QueryRevisionByIdUseCase;
import hexarch.dms.preparation.application.port.in.RevisionQueryModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(RevisionRestController.class)
class RevisionRestControllerTest {

    private static final long REVISION_ID = 1L;
    private static final long DOCUMENT_ID = 21L;

    private static final String DOCUMENT_TITLE = "Foo";

    private static final String REVISION_CONTENT = "Bar";

    @MockBean
    private QueryRevisionByIdUseCase queryRevisionByIdUseCase;

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldFindRevision() throws Exception {
        // given
        var revisionQueryModel = new RevisionQueryModel(REVISION_ID, DOCUMENT_ID, DOCUMENT_TITLE, REVISION_CONTENT);
        when(queryRevisionByIdUseCase.queryBy(REVISION_ID)).thenReturn(Optional.of(revisionQueryModel));

        // when
        var resultActions = mvc.perform(MockMvcRequestBuilders.get("/revisions/{revisionId}", REVISION_ID)
                .accept(APPLICATION_JSON_VALUE)
                .contentType(APPLICATION_JSON_VALUE));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(REVISION_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$.documentId").value(DOCUMENT_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$.documentTitle").value(DOCUMENT_TITLE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.revisionContent").value(REVISION_CONTENT));
        verify(queryRevisionByIdUseCase, times(1)).queryBy(REVISION_ID);
    }

    @Test
    void shouldNotFindRevision() throws Exception {
        // given
        when(queryRevisionByIdUseCase.queryBy(REVISION_ID)).thenReturn(Optional.empty());

        // when
        var resultActions = mvc.perform(MockMvcRequestBuilders.get("/revisions/{revisionId}", REVISION_ID)
                .accept(APPLICATION_JSON_VALUE)
                .contentType(APPLICATION_JSON_VALUE));

        // then
        resultActions.andExpect(status().isNotFound());
        verify(queryRevisionByIdUseCase, times(1)).queryBy(REVISION_ID);
    }

}