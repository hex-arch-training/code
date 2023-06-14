package hexarch.dms.preparation.application.port.out;

import hexarch.dms.preparation.domain.Revision;

import java.util.Optional;

public interface GetRevisionPort {
    Optional<Revision> findById(long revisionId);
}
