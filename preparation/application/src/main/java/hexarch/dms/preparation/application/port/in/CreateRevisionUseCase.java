package hexarch.dms.preparation.application.port.in;

import hexarch.dms.preparation.domain.CreateRevisionCommand;

public interface CreateRevisionUseCase {
    Long apply(CreateRevisionCommand command);
}
