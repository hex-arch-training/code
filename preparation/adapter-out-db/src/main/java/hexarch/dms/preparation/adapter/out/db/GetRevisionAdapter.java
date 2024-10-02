package hexarch.dms.preparation.adapter.out.db;

import hexarch.dms.preparation.application.port.out.GetRevisionPort;
import hexarch.dms.preparation.domain.Revision;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class GetRevisionAdapter implements GetRevisionPort  {

    private final RevisionRepository revisionRepository;

    @Override
    public Optional<Revision> findById(final long revisionId) {
        return revisionRepository.findById(revisionId);
    }
}
