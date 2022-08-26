package hexarch.dms.preparation.application.port.out;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PushRevisionToVerificationCommand {
    private final long revisionId;
}
