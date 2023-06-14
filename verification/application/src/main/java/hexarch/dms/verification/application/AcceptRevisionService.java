package hexarch.dms.verification.application;

import hexarch.dms.verification.application.port.in.AcceptRevisionUseCase;
import hexarch.dms.verification.application.port.out.FindRevisionVerificationPort;
import hexarch.dms.verification.application.port.out.GetRevisionVerificationPort;
import hexarch.dms.verification.application.port.out.GetSecurityContextPort;
import hexarch.dms.verification.application.port.out.SaveRevisionVerificationPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class AcceptRevisionService implements AcceptRevisionUseCase {

    private final GetRevisionVerificationPort getRevisionVerificationPort;
    private final SaveRevisionVerificationPort saveRevisionVerificationPort;

    private final GetSecurityContextPort getSecurityContextPort;

    @Override
    public void apply(final AcceptRevisionCommand command) {
        var revisionVerification = getRevisionVerificationPort.findById(command.revisionId())
                .orElseThrow(() -> new RevisionVerificationNotFoundException(command.revisionId()));
        var currentUser = getSecurityContextPort.getCurrentUser();
        revisionVerification.accept(currentUser);
        saveRevisionVerificationPort.save(revisionVerification);
    }
}
