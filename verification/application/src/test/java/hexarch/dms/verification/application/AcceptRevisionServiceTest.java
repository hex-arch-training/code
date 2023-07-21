package hexarch.dms.verification.application;

import hexarch.dms.verification.application.port.in.AcceptRevisionCommand;
import hexarch.dms.verification.application.port.out.FindRevisionVerificationPort;
import hexarch.dms.verification.application.port.out.GetSecurityContextPort;
import hexarch.dms.verification.application.port.out.SaveRevisionVerificationPort;
import hexarch.dms.verification.domain.DocumentRevisionId;
import hexarch.dms.verification.domain.RevisionVerification;
import hexarch.dms.verification.domain.User;
import hexarch.dms.verification.domain.VerificationStatus;
import hexarch.dms.verification.domain.VerificationStatusException;
import hexarch.dms.verification.domain.VerificationUserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AcceptRevisionServiceTest {

    private static final DocumentRevisionId DOCUMENT_REVISION_ID = new DocumentRevisionId(44L);

    @Mock
    private FindRevisionVerificationPort findRevisionVerificationPort;
    @Mock
    private SaveRevisionVerificationPort saveRevisionVerificationPort;
    @Mock
    private GetSecurityContextPort getSecurityContextPort;

    @InjectMocks
    private AcceptRevisionService acceptRevisionService;

    @Captor
    private ArgumentCaptor<RevisionVerification> revisionVerificationCaptor;

    @Test
    public void shouldSave() {
        // given
        var userOne = new User("one");
        var userTwo = new User("two");
        var command = new AcceptRevisionCommand(DOCUMENT_REVISION_ID);
        var revisionVerification = RevisionVerification.createNew(command.revisionId(), userOne);
        when(findRevisionVerificationPort.findByRevisionId(DOCUMENT_REVISION_ID)).thenReturn(Optional.of(revisionVerification));
        when(getSecurityContextPort.getCurrentUser()).thenReturn(userTwo);

        // when
        acceptRevisionService.apply(command);

        // then
        verify(saveRevisionVerificationPort, times(1)).save(revisionVerificationCaptor.capture());
        assertThat(revisionVerificationCaptor.getValue().getVerificationStatus()).isEqualTo(VerificationStatus.ACCEPTED);
    }

    @Test
    public void shouldThrowExceptionIfTheSameUser() {
        // given
        var user = new User("one");
        var command = new AcceptRevisionCommand(DOCUMENT_REVISION_ID);
        var revisionVerification = RevisionVerification.createNew(command.revisionId(), user);
        when(findRevisionVerificationPort.findByRevisionId(DOCUMENT_REVISION_ID)).thenReturn(Optional.of(revisionVerification));
        when(getSecurityContextPort.getCurrentUser()).thenReturn(user);

        // when
        assertThatThrownBy(() -> acceptRevisionService.apply(command))
                .isInstanceOf(VerificationUserException.class);

        // then
        verify(saveRevisionVerificationPort, never()).save(any());
    }

    @Test
    public void shouldThrowExceptionIfNotAcceptableStatus() {
        // given
        var userOne = new User("one");
        var userTwo = new User("two");
        var command = new AcceptRevisionCommand(DOCUMENT_REVISION_ID);
        var revisionVerification = RevisionVerification.createNew(command.revisionId(), userOne);
        revisionVerification.accept(userTwo);
        when(findRevisionVerificationPort.findByRevisionId(DOCUMENT_REVISION_ID)).thenReturn(Optional.of(revisionVerification));
        when(getSecurityContextPort.getCurrentUser()).thenReturn(userTwo);

        // when
        assertThatThrownBy(() -> acceptRevisionService.apply(command))
                .isInstanceOf(VerificationStatusException.class);

        // then
        verify(saveRevisionVerificationPort, never()).save(any());
    }

    @Test
    public void shouldThrowExceptionIfMissingData() {
        // given
        var command = new AcceptRevisionCommand(DOCUMENT_REVISION_ID);
        when(findRevisionVerificationPort.findByRevisionId(DOCUMENT_REVISION_ID)).thenReturn(Optional.ofNullable(null));

        // when
        assertThatThrownBy(() -> acceptRevisionService.apply(command))
                .isInstanceOf(VerificationRequestNotFoundException.class);

        // then
        verify(getSecurityContextPort, never()).getCurrentUser();
        verify(saveRevisionVerificationPort, never()).save(any());
    }

}
