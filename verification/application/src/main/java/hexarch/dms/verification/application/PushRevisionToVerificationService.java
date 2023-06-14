package hexarch.dms.verification.application;

import hexarch.dms.verification.application.port.in.PushRevisionToVerificationUseCase;
import hexarch.dms.verification.application.port.out.GetSecurityContextPort;
import hexarch.dms.verification.application.port.out.SaveRevisionVerificationPort;
import hexarch.dms.verification.domain.RevisionVerification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class PushRevisionToVerificationService implements PushRevisionToVerificationUseCase {

    // TODO: this is fishy!
    private final GetSecurityContextPort getSecurityContextPort;
    private final SaveRevisionVerificationPort saveRevisionVerificationPort;
    @Override
    public long apply(final PushRevisionToVerificationCommand command) {
        var revisionVerification = RevisionVerification.createNew(command.revisionId(), getSecurityContextPort.getCurrentUser());
        return saveRevisionVerificationPort.save(revisionVerification);
    }
}
