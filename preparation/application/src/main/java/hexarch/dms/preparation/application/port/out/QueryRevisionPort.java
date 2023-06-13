package hexarch.dms.preparation.application.port.out;

import hexarch.dms.preparation.application.port.in.RevisionQueryModel;

import java.util.Optional;

public interface QueryRevisionPort {
    Optional<RevisionQueryModel> queryBy(long id);
}
