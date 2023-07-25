package hexarch.dms.preparation.application.port.out;

public interface PublishRevisionVerificationRequestedEventPort {

    void publish(RevisionVerificationRequestedEvent event);
}
