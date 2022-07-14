package hexarch.dms.verification.application;

import hexarch.dms.verification.application.port.in.PushRevisionToVerificationCommand;
import hexarch.dms.verification.application.port.in.PushRevisionToVerificationUseCase;
import hexarch.dms.verification.application.port.out.SaveRevisionVerificationPort;
import hexarch.dms.verification.domain.DocumentRevisionId;
import hexarch.dms.verification.domain.RevisionVerification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PushRevisionToVerificationService implements PushRevisionToVerificationUseCase {

    private final SaveRevisionVerificationPort saveRevisionVerificationPort;

    @Transactional
    @Override
    public void apply(PushRevisionToVerificationCommand command) {
        var request = RevisionVerification.createNew(new DocumentRevisionId(command.getRevisionId()));
        saveRevisionVerificationPort.save(request);
    }
}
