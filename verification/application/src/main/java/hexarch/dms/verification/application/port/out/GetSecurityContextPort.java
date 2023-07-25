package hexarch.dms.verification.application.port.out;

import hexarch.dms.verification.domain.User;

public interface GetSecurityContextPort {
    User getCurrentUser();
}
