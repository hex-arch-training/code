package hexarch.dms.verification.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PushRevisionToVerificationCommand {
    private final long revisionId;
}
