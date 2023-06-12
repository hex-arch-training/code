package hexarch.dms.verification.application;

import hexarch.dms.verification.application.port.in.AcceptRevisionUseCase;
import hexarch.dms.verification.application.port.out.FindRevisionVerificationPort;
import hexarch.dms.verification.application.port.out.GetSecurityContextPort;
import hexarch.dms.verification.application.port.out.SaveRevisionVerificationPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
class AcceptRevisionService implements AcceptRevisionUseCase {

    private final FindRevisionVerificationPort findRevisionVerificationPort;

    private final SaveRevisionVerificationPort saveRevisionVerificationPort;

    private final GetSecurityContextPort getSecurityContextPort;

    @Transactional
    @Override
    public void apply(final AcceptRevisionCommand command) {
        var request = findRevisionVerificationPort.findByRevisionId(command.revisionId())
                .orElseThrow(() -> new VerificationRequestNotFoundException(command.revisionId()));
        request.accept(getSecurityContextPort.getCurrentUser());
        saveRevisionVerificationPort.save(request);
    }
}
