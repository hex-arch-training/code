package hexarch.dms.verification.adapter.in.web;

import hexarch.dms.verification.application.port.in.AcceptRevisionCommand;
import hexarch.dms.verification.application.port.in.AcceptRevisionUseCase;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RevisionVerificationRestController.class)
class RevisionVerificationRestControllerTest {

    private static final Long REVISION_ID = 1L;

    @MockBean
    private AcceptRevisionUseCase acceptRevisionUseCase;

    @Autowired
    private MockMvc mvc;

    @Captor
    private ArgumentCaptor<AcceptRevisionCommand> acceptRevisionCommandCaptor;

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
}