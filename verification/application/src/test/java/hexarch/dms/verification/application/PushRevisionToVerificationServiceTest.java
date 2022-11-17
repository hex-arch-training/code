package hexarch.dms.verification.application;

import hexarch.dms.verification.application.port.in.PushRevisionToVerificationCommand;
import hexarch.dms.verification.application.port.out.GetSecurityContextPort;
import hexarch.dms.verification.application.port.out.SaveRevisionVerificationPort;
import hexarch.dms.verification.domain.RevisionVerification;
import hexarch.dms.verification.domain.User;
import hexarch.dms.verification.domain.VerificationStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PushRevisionToVerificationServiceTest {

    @Mock
    private SaveRevisionVerificationPort saveRevisionVerificationPort;

    @Mock
    private GetSecurityContextPort getSecurityContextPort;

    @InjectMocks
    private PushRevisionToVerificationService pushRevisionToVerificationService;

    @Captor
    private ArgumentCaptor<RevisionVerification> revisionVerificationCaptor;

    @Test
    void shouldSaveRevisionVerification() {
        // given
        long revisionId = 1;
        PushRevisionToVerificationCommand pushRevisionToVerificationCommand = new PushRevisionToVerificationCommand(revisionId);
        var user = new User("user");
        when(getSecurityContextPort.getCurrentUser()).thenReturn(user);

        // when
        pushRevisionToVerificationService.apply(pushRevisionToVerificationCommand);

        // then
        verify(saveRevisionVerificationPort, times(1)).save(revisionVerificationCaptor.capture());
        assertThat(revisionVerificationCaptor.getValue().getVerificationStatus()).isEqualTo(VerificationStatus.PENDING);
        assertThat(revisionVerificationCaptor.getValue().getDocumentRevisionId().getRevisionId()).isEqualTo(revisionId);
        assertThat(revisionVerificationCaptor.getValue().getCreatedBy()).isEqualTo(user);

    }
}
