package hexarch.dms.preparation.application.port.out;

import hexarch.dms.preparation.domain.Revision;

public interface SaveRevisionPort {
    Long saveRevision(Revision revision);
}
