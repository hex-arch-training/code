package hexarch.dms.preparation.application;

import hexarch.dms.preparation.application.port.in.CreateRevisionUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service // we allow using Spring annotations to create services
@AllArgsConstructor
// watch the package private access!
class CreateRevisionService implements CreateRevisionUseCase {

}
