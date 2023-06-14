package hexarch.dms.verification.adapter.out.db;

import hexarch.dms.verification.application.port.out.SaveRevisionVerificationPort;
import hexarch.dms.verification.domain.RevisionVerification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class SaveRevisionVerificationAdapter implements SaveRevisionVerificationPort {

    private final RevisionVerificationRepository revisionVerificationRepository;
    @Override
    public long save(final RevisionVerification revisionVerification) {
        var saved = revisionVerificationRepository.save(revisionVerification);
        return saved.getId();
    }
}
