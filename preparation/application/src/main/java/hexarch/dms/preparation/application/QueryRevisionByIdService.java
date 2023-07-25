package hexarch.dms.preparation.application;

import java.util.Optional;

import hexarch.dms.preparation.application.port.RevisionQueryModel;
import hexarch.dms.preparation.application.port.in.QueryRevisionByIdUseCase;
import hexarch.dms.preparation.application.port.out.QueryRevisionPort;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class QueryRevisionByIdService implements QueryRevisionByIdUseCase {

    private final QueryRevisionPort queryRevisionPort;

    @Transactional
    @Override
    public Optional<RevisionQueryModel> queryBy(@NonNull Long revisionId) {
        return queryRevisionPort.queryById(revisionId);
    }
}
