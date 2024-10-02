package hexarch.dms.verification.application;

import hexarch.dms.verification.application.port.in.PushRevisionToVerificationCommand;
import hexarch.dms.verification.application.port.in.PushRevisionToVerificationUseCase;
import hexarch.dms.verification.application.port.out.GetSecurityContextPort;
import hexarch.dms.verification.application.port.out.SaveRevisionVerificationPort;
import hexarch.dms.verification.domain.RevisionVerification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PushRevisionToVerificationService implements PushRevisionToVerificationUseCase {

    private final GetSecurityContextPort getSecurityContextPort;
    private final SaveRevisionVerificationPort saveRevisionVerificationPort;

    @Override
    public long apply(final PushRevisionToVerificationCommand command) {
        var revisionVerification = RevisionVerification.createNew(
                command.id(), getSecurityContextPort.getCurrentUser());
        return saveRevisionVerificationPort.save(revisionVerification);
    }
}
