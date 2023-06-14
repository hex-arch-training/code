package hexarch.dms.verification.application;

import hexarch.dms.verification.application.port.RevisionVerificationQueryModel;
import hexarch.dms.verification.application.port.in.QueryRevisionVerificationUseCase;
import hexarch.dms.verification.application.port.out.FindRevisionVerificationPort;
import hexarch.dms.verification.domain.DocumentRevisionId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
class QueryRevisionVerificationService implements QueryRevisionVerificationUseCase {

    private final FindRevisionVerificationPort findRevisionVerificationPort;
    @Override
    public Optional<RevisionVerificationQueryModel> queryBy(final DocumentRevisionId id) {
        return findRevisionVerificationPort.findBy(id);
    }
}
