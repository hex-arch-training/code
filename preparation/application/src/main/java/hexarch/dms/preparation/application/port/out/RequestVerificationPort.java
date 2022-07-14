package hexarch.dms.preparation.application.port.out;

import hexarch.dms.shared.domain.RevisionVerificationRequestedEvent;

public interface RequestVerificationPort {
    void dispatch(RevisionVerificationRequestedEvent event);
}
