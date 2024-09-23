package hexarch.dms.verification.domain;

import hexarch.dms.verification.domain.converters.DocumentRevisionIdConverter;
import hexarch.dms.verification.domain.converters.UserConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.EnumSet;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PACKAGE)
public class RevisionVerification {

    private static final EnumSet<VerificationStatus> acceptableStatuses = EnumSet.of(VerificationStatus.PENDING);
    private static final EnumSet<VerificationStatus> approvableStatuses = EnumSet.of(VerificationStatus.ACCEPTED);
    private static final EnumSet<VerificationStatus> rejectableStatuses = EnumSet.of(VerificationStatus.PENDING, VerificationStatus.ACCEPTED);

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    @Convert(converter = DocumentRevisionIdConverter.class)
    private DocumentRevisionId documentRevisionId;

    @Column(nullable = false)
    @Convert(converter = UserConverter.class)
    private User createdBy;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VerificationStatus verificationStatus;

    /**
     * Marks that revision has been verified and accepted.
     */
    public void accept(User currentUser) {
        if (currentUser.equals(createdBy)) {
            throw new VerificationUserException(currentUser);
        }
        if (!acceptableStatuses.contains(verificationStatus)) {
            throw new VerificationStatusException(verificationStatus, VerificationStatus.ACCEPTED);
        }
        verificationStatus = VerificationStatus.ACCEPTED;
    }

    /**
     * Marks that accepted revision has been approved by higher management.
     */
    public void approve() {
        if (!approvableStatuses.contains(verificationStatus)) {
            throw new VerificationStatusException(verificationStatus, VerificationStatus.APPROVED);
        }
        verificationStatus = VerificationStatus.APPROVED;
    }

    /**
     * Marks that revision has been rejected.
     */
    public void reject() {
        if (!rejectableStatuses.contains(verificationStatus)) {
            throw new VerificationStatusException(verificationStatus, VerificationStatus.REJECTED);
        }
        verificationStatus = VerificationStatus.REJECTED;
    }

    public static RevisionVerification createNew(DocumentRevisionId documentRevisionId, User createdBy) {
        var request = new RevisionVerification();
        request.documentRevisionId = documentRevisionId;
        request.verificationStatus = VerificationStatus.PENDING;
        request.createdBy = createdBy;
        return request;
    }
}
