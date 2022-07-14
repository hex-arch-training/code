package hexarch.dms.verification.domain;

public class VerificationStatusException extends RuntimeException {
    public VerificationStatusException(VerificationStatus oldStatus, VerificationStatus newStatus) {
        super(String.format("Cannot change verification status from %s to %s", oldStatus.name(), newStatus.name()));
    }
}
