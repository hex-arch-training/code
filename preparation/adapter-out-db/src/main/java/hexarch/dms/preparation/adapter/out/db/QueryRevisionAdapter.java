package hexarch.dms.preparation.adapter.out.db;

import hexarch.dms.preparation.application.port.RevisionQueryModel;
import hexarch.dms.preparation.application.port.out.QueryRevisionPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
class QueryRevisionAdapter implements QueryRevisionPort {

    private final RevisionRepository revisionRepository;

    @Override
    public Optional<RevisionQueryModel> queryBy(final long id) {
        return revisionRepository.queryById(id);
    }
}
