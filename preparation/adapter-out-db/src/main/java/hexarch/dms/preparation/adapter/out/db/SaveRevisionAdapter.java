package hexarch.dms.preparation.adapter.out.db;

import hexarch.dms.preparation.application.port.out.SaveRevisionPort;
import hexarch.dms.preparation.domain.Revision;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class SaveRevisionAdapter implements SaveRevisionPort {

    private final RevisionRepository revisionRepository;

    @Override
    public long save(@NonNull final Revision revision) {
        return revisionRepository.save(revision).getId();
    }
}
