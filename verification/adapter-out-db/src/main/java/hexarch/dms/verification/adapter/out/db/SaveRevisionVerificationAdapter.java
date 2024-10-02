package hexarch.dms.verification.adapter.out.db;

import hexarch.dms.verification.application.port.out.SaveRevisionVerificationPort;
import hexarch.dms.verification.domain.RevisionVerification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SaveRevisionVerificationAdapter implements SaveRevisionVerificationPort {

    private final VerificationRequestRepository repository;

    @Override
    public long save(final RevisionVerification revisionVerification) {
        return repository.save(revisionVerification).getId();
    }
}
