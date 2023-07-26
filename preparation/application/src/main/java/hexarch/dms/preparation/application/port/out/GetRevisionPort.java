package hexarch.dms.preparation.application.port.out;

import java.util.Optional;

import hexarch.dms.preparation.domain.Revision;

public interface GetRevisionPort {
    Optional<Revision> getById(long revisionId);
}
