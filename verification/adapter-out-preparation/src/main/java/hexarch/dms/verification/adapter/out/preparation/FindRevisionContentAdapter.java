package hexarch.dms.verification.adapter.out.preparation;

import hexarch.dms.preparation.application.port.in.QueryRevisionByIdUseCase;
import hexarch.dms.verification.application.port.out.FindRevisionContentPort;
import hexarch.dms.verification.domain.DocumentRevisionId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
class FindRevisionContentAdapter implements FindRevisionContentPort {

    private final QueryRevisionByIdUseCase useCase;

    @Override
    public Optional<RevisionContent> findById(final DocumentRevisionId revisionId) {
        return useCase
                .queryBy(revisionId.getRevisionId())
                .map(revisionQueryModel ->
                        new RevisionContent(
                                revisionQueryModel.documentTitle(),
                                revisionQueryModel.revisionContent()));
    }
}
