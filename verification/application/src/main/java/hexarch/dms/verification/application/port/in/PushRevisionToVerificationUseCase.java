package hexarch.dms.verification.application.port.in;

public interface PushRevisionToVerificationUseCase {

    long apply(PushRevisionToVerificationCommand command);
}
