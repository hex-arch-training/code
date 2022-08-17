package hexarch.dms.shared.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RevisionVerificationRequestedEvent {
    private final long revisionId;
}
