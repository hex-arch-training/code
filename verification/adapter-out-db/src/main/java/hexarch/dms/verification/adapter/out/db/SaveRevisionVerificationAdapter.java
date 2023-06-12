package hexarch.dms.verification.adapter.out.db;

import hexarch.dms.verification.application.port.out.SaveRevisionVerificationPort;
import hexarch.dms.verification.domain.RevisionVerification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class SaveRevisionVerificationAdapter implements SaveRevisionVerificationPort {

    private final VerificationRequestRepository verificationRequestRepository;

    @Override
    public long save(final RevisionVerification request) {
        var savedRequest = verificationRequestRepository.save(request);
        return savedRequest.getId();
    }
}
