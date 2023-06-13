package hexarch.dms.preparation.adapter.in.web;

import hexarch.dms.preparation.application.port.in.CreateRevisionUseCase;
import hexarch.dms.preparation.domain.DocumentTitle;
import hexarch.dms.preparation.domain.RevisionContent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CreateRevisionRestAdapter.class)
class CreateRevisionRestAdapterTest {

    private static final String DOCUMENT_TITLE = "Foo";
    private static final String REVISION_CONTENT = "Bar";

    private static final long REVISION_ID = 35L;

    @MockBean
    private CreateRevisionUseCase createRevisionUseCase;

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldCreateRevision() throws Exception {
        // given
        var command = new CreateRevisionUseCase.CreateRevisionCommand(
                new DocumentTitle(DOCUMENT_TITLE), new RevisionContent(REVISION_CONTENT));
        when(createRevisionUseCase.apply(eq(command))).thenReturn(REVISION_ID);

        // when
        var resultActions = mvc.perform(post("/revisions")
                .content("""
                        {
                            "documentTitle": "%s",
                            "revisionContent": "%s"
                        }
                        """.formatted(DOCUMENT_TITLE, REVISION_CONTENT))
                .accept(APPLICATION_JSON_VALUE)
                .contentType(APPLICATION_JSON_VALUE));

        // then
        resultActions
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(content().string(String.valueOf(REVISION_ID)));
        verify(createRevisionUseCase, times(1)).apply(command);
    }

    @Test
    void shouldFailOnValidation() throws Exception {
        // given
        var faultyTitle = "Some other titleSome other titleSome other titleSome other" +
                           "titleSome other titleSome other titleSome other titleSome other titleSome other" +
                           "titleSome other titleSome other titleSome other titleSome other titleSome other" +
                           " titleSome other titleSome other titleSome other titleSome other titleSome other" +
                           " titleSome other titleSome other titleSome other titleSome other titleSome other" +
                           " titleSome other titleSome other titleSome other titleSome other titleSome other" +
                           " titleSome other titleSome other titleSome other titleSome other titleSome other" +
                           "  titleSome other title";
        // when
        var resultActions = mvc.perform(post("/revisions")
                .content("""
                        {
                            "documentTitle": "%s",
                            "revisionContent": "%s"
                        }
                        """.formatted(faultyTitle, REVISION_CONTENT))
                .accept(APPLICATION_JSON_VALUE)
                .contentType(APPLICATION_JSON_VALUE));
        // then
        resultActions
                .andExpect(status().is(422));
    }
}