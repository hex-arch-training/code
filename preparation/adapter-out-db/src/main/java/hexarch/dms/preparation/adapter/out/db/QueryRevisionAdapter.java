package hexarch.dms.preparation.adapter.out.db;

import java.util.Optional;

import hexarch.dms.preparation.application.port.RevisionQueryModel;
import hexarch.dms.preparation.application.port.out.QueryRevisionPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class QueryRevisionAdapter implements QueryRevisionPort {

    private final RevisionRepository revisionRepository;

    @Override
    public Optional<RevisionQueryModel> queryById(Long revisionId) {
        var revisions = revisionRepository.queryById(revisionId);
        return revisions.isEmpty() ? Optional.empty() : Optional.of(revisions.get(0));
    }
}
