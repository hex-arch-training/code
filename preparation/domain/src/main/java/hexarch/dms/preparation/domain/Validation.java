package hexarch.dms.preparation.domain;

import lombok.NonNull;

import java.util.Objects;

public abstract class Validation {
    private Validation() {
        throw new IllegalStateException("Utility class!");
    }

    public static void requireNonBlank(String value, @NonNull String fieldName) {
        if (Objects.isNull(value) || value.isBlank()) {
            throw new ValidationException(fieldName.concat(" must not be blank"));
        }
    }

    public static void requireMaxLength(String value, int maxLength, @NonNull String fieldName) {
        if (Objects.isNull(value) || value.length() > maxLength) {
            throw new ValidationException(
                    fieldName.concat(" exceeds the maximal length: ".concat(String.valueOf(maxLength))));
        }
    }
}
