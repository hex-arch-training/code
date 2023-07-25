package hexarch.dms.preparation.adapter.out.db;

import java.util.Optional;

import hexarch.dms.preparation.application.port.out.GetRevisionPort;
import hexarch.dms.preparation.domain.Revision;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetRevisionAdapter implements GetRevisionPort  {

    private final RevisionRepository revisionRepository;

    @Override
    public Optional<Revision> getById(long revisionId) {
        return revisionRepository.findById(revisionId);
    }
}
