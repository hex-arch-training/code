package hexarch.dms.preparation.domain;

import hexarch.dms.preparation.domain.converters.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static hexarch.dms.preparation.domain.Validation.requireMaxLength;
import static hexarch.dms.preparation.domain.Validation.requireNonBlank;

@Getter
@ToString
@EqualsAndHashCode
public class RevisionContent implements ValueObject<String> {
    public static final int MAX_LENGTH = 300;
    private static final String NAME = "Revision content";

    private final String value;

    public RevisionContent(String content) {
        requireNonBlank(content, NAME);
        requireMaxLength(content, MAX_LENGTH, NAME);
        this.value = content;
    }
}
