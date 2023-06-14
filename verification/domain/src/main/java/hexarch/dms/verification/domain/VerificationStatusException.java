package hexarch.dms.verification.domain;

public class VerificationStatusException extends RuntimeException {
    public VerificationStatusException(VerificationStatus oldStatus, VerificationStatus newStatus) {
        super("Cannot change verification status from %s to %s".formatted(oldStatus.name(), newStatus.name()));
    }
}
