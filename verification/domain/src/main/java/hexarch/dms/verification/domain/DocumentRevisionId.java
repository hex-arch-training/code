package hexarch.dms.verification.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
public class DocumentRevisionId {

    @Getter
    private final long revisionId;
}
