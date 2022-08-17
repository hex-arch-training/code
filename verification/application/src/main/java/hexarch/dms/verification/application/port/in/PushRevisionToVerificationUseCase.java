package hexarch.dms.verification.application.port.in;

public interface PushRevisionToVerificationUseCase {
    void apply(PushRevisionToVerificationCommand command);
}
