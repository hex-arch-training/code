package hexarch.dms.verification.adapter.out.db;

import hexarch.dms.verification.application.port.out.FindRevisionVerificationPort;
import hexarch.dms.verification.domain.DocumentRevisionId;
import hexarch.dms.verification.domain.RevisionVerification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class FindRevisionVerificationAdapter implements FindRevisionVerificationPort {

    private final VerificationRequestRepository verificationRequestRepository;

    @Override
    public Optional<RevisionVerification> findByRevisionId(DocumentRevisionId revisionId) {
        return verificationRequestRepository.findByRevisionId(revisionId);
    }
}
