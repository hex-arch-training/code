package hexarch.dms.preparation.application.port.out;

public interface PushRevisionToVerificationPort {
    void apply(PushRevisionToVerificationCommand command);
}
