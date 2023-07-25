package hexarch.dms.preparation.application.port.in;

import java.util.Optional;

import hexarch.dms.preparation.application.port.RevisionQueryModel;

public interface QueryRevisionByIdUseCase {

    Optional<RevisionQueryModel> queryBy(Long revisionId);

}
