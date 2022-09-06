package hexarch.dms.verification.application;

import hexarch.dms.preparation.application.port.in.QueryRevisionByIdUseCase;
import hexarch.dms.verification.application.port.RevisionPreviewQueryModel;
import hexarch.dms.verification.application.port.in.QueryRevisionPreviewUseCase;
import hexarch.dms.verification.application.port.out.FindRevisionVerificationPort;
import hexarch.dms.verification.domain.DocumentRevisionId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class QueryRevisionPreviewService implements QueryRevisionPreviewUseCase {

    private final FindRevisionVerificationPort findRevisionVerificationPort;

    private final QueryRevisionByIdUseCase queryRevisionByIdUseCase;

    @Override
    public Optional<RevisionPreviewQueryModel> queryBy(DocumentRevisionId revisionId) {
        return queryRevisionByIdUseCase.queryBy(revisionId.getRevisionId())
                .flatMap(revisionQueryModel ->
                        findRevisionVerificationPort.findByRevisionId(revisionId)
                                .map(revisionVerification ->
                                        new RevisionPreviewQueryModel(
                                                revisionQueryModel.getDocumentTitle(),
                                                revisionQueryModel.getContent(),
                                                revisionVerification.getDocumentRevisionId(),
                                                revisionVerification.getVerificationStatus())));
    }
}
