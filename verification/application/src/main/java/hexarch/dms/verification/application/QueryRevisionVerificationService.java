package hexarch.dms.verification.application;

import java.util.Optional;

import hexarch.dms.verification.application.port.VerificationRequestModel;
import hexarch.dms.verification.application.port.in.QueryRevisionVerificationUseCase;
import hexarch.dms.verification.application.port.out.FindRevisionVerificationPort;
import hexarch.dms.verification.domain.DocumentRevisionId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QueryRevisionVerificationService implements QueryRevisionVerificationUseCase {
    private final FindRevisionVerificationPort findRevisionVerificationPort;

    @Override
    public Optional<VerificationRequestModel> queryBy(DocumentRevisionId revisionId) {
        return findRevisionVerificationPort.findByRevisionId(revisionId)
                .map(request -> new VerificationRequestModel(
                        request.getDocumentRevisionId(),
                        request.getVerificationStatus()
                ));
    }
}
