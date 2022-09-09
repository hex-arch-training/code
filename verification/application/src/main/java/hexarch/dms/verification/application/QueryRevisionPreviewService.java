package hexarch.dms.verification.application;

import hexarch.dms.verification.application.port.RevisionPreviewQueryModel;
import hexarch.dms.verification.application.port.in.QueryRevisionPreviewUseCase;
import hexarch.dms.verification.application.port.out.FindRevisionContentPort;
import hexarch.dms.verification.application.port.out.FindRevisionVerificationPort;
import hexarch.dms.verification.domain.DocumentRevisionId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class QueryRevisionPreviewService implements QueryRevisionPreviewUseCase {

    private final FindRevisionVerificationPort findRevisionVerificationPort;

    private final FindRevisionContentPort findRevisionContentPort;

    @Override
    public Optional<RevisionPreviewQueryModel> queryBy(DocumentRevisionId revisionId) {
        return findRevisionContentPort.queryBy(revisionId)
                .flatMap(revisionQueryModel ->
                        findRevisionVerificationPort.findByRevisionId(revisionId)
                                .map(revisionVerification ->
                                        new RevisionPreviewQueryModel(
                                                revisionQueryModel.getTitle(),
                                                revisionQueryModel.getContent(),
                                                revisionVerification.getDocumentRevisionId(),
                                                revisionVerification.getVerificationStatus())));
    }
}
