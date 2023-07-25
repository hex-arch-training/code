package hexarch.dms.preparation.application.port.out;

import java.util.Optional;

import hexarch.dms.preparation.application.port.RevisionQueryModel;

public interface QueryRevisionPort {

    Optional<RevisionQueryModel> queryById(Long revisionId);

}
