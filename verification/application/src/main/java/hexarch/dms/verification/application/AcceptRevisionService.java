package hexarch.dms.verification.application;

import hexarch.dms.verification.application.port.in.AcceptRevisionCommand;
import hexarch.dms.verification.application.port.in.AcceptRevisionUseCase;
import hexarch.dms.verification.application.port.out.FindRevisionVerificationPort;
import hexarch.dms.verification.application.port.out.GetSecurityContextPort;
import hexarch.dms.verification.application.port.out.SaveRevisionVerificationPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AcceptRevisionService implements AcceptRevisionUseCase {

    private final FindRevisionVerificationPort findRevisionVerificationPort;

    private final SaveRevisionVerificationPort saveRevisionVerificationPort;

    private final GetSecurityContextPort getSecurityContextPort;

    @Override
    public void apply(final AcceptRevisionCommand command) {
        var revisionVerification = findRevisionVerificationPort.findByRevisionId(command.revisionId())
                .orElseThrow(() -> new RuntimeException("Revision verification not found"));
        revisionVerification.accept(getSecurityContextPort.getCurrentUser());
        saveRevisionVerificationPort.save(revisionVerification);
    }
}
