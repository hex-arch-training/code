package hexarch.dms.verification.domain;

import hexarch.dms.verification.domain.converters.DocumentRevisionIdConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.EnumSet;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RevisionVerification {

    private static final EnumSet<VerificationStatus> acceptableStatuses = EnumSet.of(VerificationStatus.PENDING);
    private static final EnumSet<VerificationStatus> approvableStatuses = EnumSet.of(VerificationStatus.ACCEPTED);
    private static final EnumSet<VerificationStatus> rejectableStatuses = EnumSet.of(VerificationStatus.PENDING, VerificationStatus.ACCEPTED);

    @Id
    @GeneratedValue
    @Getter
    private Long id;

    @Column(nullable = false, unique = true)
    @Convert(converter = DocumentRevisionIdConverter.class)
    @Getter
    private DocumentRevisionId documentRevisionId;

    @Column(nullable = false)
    @Getter
    @Enumerated(EnumType.STRING)
    private VerificationStatus verificationStatus;

    /**
     * Marks that revision has been verified and accepted.
     */
    public void accept() {
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

    public static RevisionVerification createNew(DocumentRevisionId documentRevisionId) {
        var request = new RevisionVerification();
        request.documentRevisionId = documentRevisionId;
        request.verificationStatus = VerificationStatus.PENDING;
        return request;
    }
}
