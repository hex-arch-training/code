package hexarch.dms.verification.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RevisionVerificationTest {

    @Test
    public void shouldAcceptRevision() {
        // given
        User user = new User("user");
        User root = new User("root");
        RevisionVerification revisionVerification = RevisionVerification.builder()
                .verificationStatus(VerificationStatus.PENDING)
                .createdBy(user)
                .build();

        // when
        revisionVerification.accept(root);

        // then
        assertThat(revisionVerification.getVerificationStatus()).isEqualTo(VerificationStatus.ACCEPTED);
    }

    @Test
    public void shouldNotAcceptRevisionTwice() {
        // given
        User user = new User("user");
        User root = new User("root");
        RevisionVerification revisionVerification = RevisionVerification.builder()
                .verificationStatus(VerificationStatus.ACCEPTED)
                .createdBy(user)
                .build();

        // when, then
        assertThatThrownBy(() -> revisionVerification.accept(root))
                .isInstanceOf(VerificationStatusException.class);
    }

    @Test
    public void shouldNotAcceptRevisionByTheSameUser() {
        // given
        User user = new User("user");
        RevisionVerification revisionVerification = RevisionVerification.builder()
                .verificationStatus(VerificationStatus.PENDING)
                .createdBy(user)
                .build();

        // when, then
        assertThatThrownBy(() -> revisionVerification.accept(user))
                .isInstanceOf(VerificationUserException.class);
    }

    @Test
    public void shouldApproveRevision() {
        // given
        RevisionVerification revisionVerification = RevisionVerification.builder()
                .verificationStatus(VerificationStatus.ACCEPTED)
                .build();

        // when
        revisionVerification.approve();

        // then
        assertThat(revisionVerification.getVerificationStatus()).isEqualTo(VerificationStatus.APPROVED);
    }

    @Test
    public void shouldNotApproveRevisionTwice() {
        // given
        RevisionVerification revisionVerification = RevisionVerification.builder()
                .verificationStatus(VerificationStatus.APPROVED)
                .build();

        // when, then
        assertThatThrownBy(() -> revisionVerification.approve())
                .isInstanceOf(VerificationStatusException.class);
    }

    @Test
    public void shouldRejectPendingRevision() {
        // given
        RevisionVerification revisionVerification = RevisionVerification.builder()
                .verificationStatus(VerificationStatus.PENDING)
                .build();

        // when
        revisionVerification.reject();

        // then
        assertThat(revisionVerification.getVerificationStatus()).isEqualTo(VerificationStatus.REJECTED);
    }

    @Test
    public void shouldRejectAcceptedRevision() {
        // given
        RevisionVerification revisionVerification = RevisionVerification.builder()
                .verificationStatus(VerificationStatus.ACCEPTED)
                .build();

        // when
        revisionVerification.reject();

        // then
        assertThat(revisionVerification.getVerificationStatus()).isEqualTo(VerificationStatus.REJECTED);
    }

    @Test
    public void shouldNotRejectRevisionTwice() {
        // given
        RevisionVerification revisionVerification = RevisionVerification.builder()
                .verificationStatus(VerificationStatus.REJECTED)
                .build();

        // when, then
        assertThatThrownBy(() -> revisionVerification.reject())
                .isInstanceOf(VerificationStatusException.class);
    }
}
