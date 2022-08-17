package hexarch.dms.shared.domain;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class RevisionVerificationRequestedEvent extends ApplicationEvent {
    private final long revisionId;

    public RevisionVerificationRequestedEvent(Object source, long revisionId) {
        super(source);
        this.revisionId = revisionId;
    }
}
