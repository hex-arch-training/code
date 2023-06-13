package hexarch.dms.preparation.application;

import hexarch.dms.preparation.application.port.in.QueryRevisionByIdUseCase;
import hexarch.dms.preparation.application.port.RevisionQueryModel;
import hexarch.dms.preparation.application.port.out.QueryRevisionPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
class QueryRevisionByIdService implements QueryRevisionByIdUseCase {

    private final QueryRevisionPort queryRevisionPort;

    @Override
    public Optional<RevisionQueryModel> queryBy(final long id) {
        return queryRevisionPort.queryBy(id);
    }
}
