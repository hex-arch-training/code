package hexarch.dms.preparation.application.port.in;

import hexarch.dms.preparation.application.port.RevisionQueryModel;

import java.util.Optional;

public interface QueryRevisionByIdUseCase {
    Optional<RevisionQueryModel> queryBy(Long revisionId);
}
