package hexarch.dms.preparation.application.port.in;

import java.util.Optional;

public interface QueryRevisionByIdUseCase {

    Optional<RevisionQueryModel> queryBy(long id);
}
