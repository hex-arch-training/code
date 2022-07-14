package hexarch.dms.verification.application.port.in;

import hexarch.dms.verification.domain.DocumentRevisionId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AcceptRevisionCommand {
    private final DocumentRevisionId revisionId;
}
