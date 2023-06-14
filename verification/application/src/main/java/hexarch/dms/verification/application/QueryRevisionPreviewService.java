package hexarch.dms.verification.application;

import hexarch.dms.verification.application.port.RevisionPreviewQueryModel;
import hexarch.dms.verification.application.port.in.QueryRevisionPreviewUseCase;
import hexarch.dms.verification.application.port.out.FindRevisionContentPort;
import hexarch.dms.verification.application.port.out.GetRevisionVerificationPort;
import hexarch.dms.verification.domain.DocumentRevisionId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class QueryRevisionPreviewService implements QueryRevisionPreviewUseCase {

    private final GetRevisionVerificationPort getRevisionVerificationPort;
    private final FindRevisionContentPort findRevisionContentPort;

    @Override
    public Optional<RevisionPreviewQueryModel> queryFor(final DocumentRevisionId revisionId) {
        return getRevisionVerificationPort.findById(revisionId).flatMap(
                revisionVerification -> findRevisionContentPort.findById(revisionId).map(
                        revisionContent -> new RevisionPreviewQueryModel(
                                revisionId.getRevisionId(),
                                revisionContent.title(),
                                revisionContent.content(),
                                revisionVerification.getVerificationStatus().name(),
                                revisionVerification.getCreatedBy().getLogin()
                        )
                )
        );
    }
}
