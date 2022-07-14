package hexarch.dms.preparation.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RequestVerificationCommand {
    private final long revisionId;
}
