package hexarch.dms.verification.adapter.out.db;

import hexarch.dms.verification.application.port.out.GetRevisionVerificationPort;
import hexarch.dms.verification.domain.DocumentRevisionId;
import hexarch.dms.verification.domain.RevisionVerification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class GetRevisionVerificationAdapter implements GetRevisionVerificationPort {

    private final RevisionVerificationRepository repository;

    @Override
    public Optional<RevisionVerification> findById(final DocumentRevisionId id) {
        return repository.queryByRevisionId(id);
    }
}
