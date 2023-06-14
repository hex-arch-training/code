package hexarch.dms.verification.adapter.out.db;

import hexarch.dms.verification.application.port.RevisionVerificationQueryModel;
import hexarch.dms.verification.application.port.out.FindRevisionVerificationPort;
import hexarch.dms.verification.domain.DocumentRevisionId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
class FindRevisionVerificationAdapter implements FindRevisionVerificationPort {

    private final RevisionVerificationRepository revisionVerificationRepository;

    @Override
    public Optional<RevisionVerificationQueryModel> findBy(final DocumentRevisionId revisionId) {
        return revisionVerificationRepository.queryReadModelByRevisionId(revisionId);
    }
}
