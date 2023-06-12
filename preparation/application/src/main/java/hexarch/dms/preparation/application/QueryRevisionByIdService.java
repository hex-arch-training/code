package hexarch.dms.preparation.application;

import hexarch.dms.preparation.application.port.RevisionQueryModel;
import hexarch.dms.preparation.application.port.in.QueryRevisionByIdUseCase;
import hexarch.dms.preparation.application.port.out.QueryRevisionPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
class QueryRevisionByIdService implements QueryRevisionByIdUseCase {

    private final QueryRevisionPort queryRevisionPort;
    @Override
    public Optional<RevisionQueryModel> queryBy(final long revisionId) {
        return queryRevisionPort.queryBy(revisionId);
    }
}
