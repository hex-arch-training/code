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
public class DocumentTitle implements ValueObject<String> {
    public static final int MAX_LENGTH = 50;
    private static final String NAME = "Document title";

    private final String value;

    public DocumentTitle(String value) {
        requireNonBlank(value, NAME);
        requireMaxLength(value, MAX_LENGTH, NAME);

        this.value = value;
    }
}
