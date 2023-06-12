package hexarch.dms.preparation.application.port.in;

public interface RequestVerificationUseCase {

    void apply(RequestVerificationCommand command);

    record RequestVerificationCommand(long revisionId) {}
}
