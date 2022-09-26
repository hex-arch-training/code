package hexarch.dms.preparation.application.port.out;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class PushRevisionToVerificationCommand {
    private final long revisionId;
}
