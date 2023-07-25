package hexarch.dms.preparation.adapter.out.db;

import hexarch.dms.preparation.application.port.out.SaveRevisionPort;
import hexarch.dms.preparation.domain.Revision;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SaveRevisionAdapter implements SaveRevisionPort {
    private final RevisionRepository revisionRepository;
    @Override
    public Long saveRevision(Revision revision) {
        var savedRevision = revisionRepository.save(revision);
        return savedRevision.getId();
    }
}
