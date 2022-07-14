package hexarch.dms.verification.application.port.out;

import hexarch.dms.verification.domain.RevisionVerification;

public interface SaveRevisionVerificationPort {

    long save(RevisionVerification revisionVerification);
}
