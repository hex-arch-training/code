package hexarch.dms.preparation.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hexarch.dms.preparation.application.port.in.CreateRevisionCommand;
import hexarch.dms.preparation.application.port.in.CreateRevisionUseCase;
import hexarch.dms.preparation.domain.DocumentTitle;
import hexarch.dms.preparation.domain.RevisionContent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RevisionRestController.class)
class RevisionRestControllerTest {

    private static final Long DOCUMENT_ID = 1L;
    private static final Long REVISION_ID = 1L;
    private static DocumentTitle DOCUMENT_TITLE = new DocumentTitle("title");
    private static RevisionContent REVISION_CONTENT = new RevisionContent("content");

    @MockBean
    private CreateRevisionUseCase createRevisionUseCase;

    @Autowired
    private MockMvc mvc;

    @Captor
    private ArgumentCaptor<CreateRevisionCommand> createRevisionCommandCaptor;

    private final ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    @Test
    void shouldCreateRevision() throws Exception {
        // given
        var requestBody = new CreateRevisionRequestBody(DOCUMENT_TITLE.getValue(), REVISION_CONTENT.getValue());

        when(createRevisionUseCase.apply(any(CreateRevisionCommand.class))).thenReturn(REVISION_ID);

        // when
        var resultActions = mvc.perform(post("/revision")
                .content(objectMapper.writeValueAsString(requestBody))
                .accept(APPLICATION_JSON_VALUE)
                .contentType(APPLICATION_JSON_VALUE));

        // then
        resultActions
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(content().string(REVISION_ID.toString()));
        verify(createRevisionUseCase, times(1)).apply(createRevisionCommandCaptor.capture());
        assertThat(createRevisionCommandCaptor.getValue().revisionContent()).isEqualTo(REVISION_CONTENT);
        assertThat(createRevisionCommandCaptor.getValue().documentTitle()).isEqualTo(DOCUMENT_TITLE);
    }

}
