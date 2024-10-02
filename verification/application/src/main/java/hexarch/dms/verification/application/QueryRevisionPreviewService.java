package hexarch.dms.verification.application;

import hexarch.dms.verification.application.port.RevisionPreviewQueryModel;
import hexarch.dms.verification.application.port.in.QueryRevisionPreviewUseCase;
import hexarch.dms.verification.application.port.out.FindRevisionContentPort;
import hexarch.dms.verification.application.port.out.FindRevisionVerificationPort;
import hexarch.dms.verification.domain.DocumentRevisionId;
import hexarch.dms.verification.domain.VerificationNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class QueryRevisionPreviewService implements QueryRevisionPreviewUseCase {

    private final FindRevisionVerificationPort findRevisionVerificationPort;

    private final FindRevisionContentPort findRevisionContentPort;

    @Override
    public RevisionPreviewQueryModel queryBy(final DocumentRevisionId revisionId) {
        var revisionVerification = findRevisionVerificationPort.findByRevisionId(revisionId)
                .orElseThrow(() -> new VerificationNotFoundException(revisionId));
        var content = findRevisionContentPort.findById(revisionId)
                .orElseThrow(() -> new VerificationNotFoundException(revisionId));

        return new RevisionPreviewQueryModel(
                revisionId.getRevisionId(),
                content.title(),
                content.content(),
                revisionVerification.getVerificationStatus().name(),
                revisionVerification.getCreatedBy().getLogin()
        );
    }
}
