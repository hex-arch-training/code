package hexarch.dms.preparation.application.port.out;

import hexarch.dms.preparation.application.port.RevisionQueryModel;

import java.util.Optional;

public interface QueryRevisionPort {
    Optional<RevisionQueryModel> queryById(Long revisionId);
}
