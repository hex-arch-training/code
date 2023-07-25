package hexarch.dms.verification.adapter.out.db;

import hexarch.dms.verification.application.port.out.SaveRevisionVerificationPort;
import hexarch.dms.verification.domain.RevisionVerification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SaveRevisionVerificationAdapter implements SaveRevisionVerificationPort {

    private final VerificationRequestRepository verificationRequestRepository;

    @Override
    public long save(RevisionVerification revisionVerification) {
        var savedRequest = verificationRequestRepository.save(revisionVerification);
        return savedRequest.getId();
    }
}
