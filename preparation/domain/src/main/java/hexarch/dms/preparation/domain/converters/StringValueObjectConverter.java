package hexarch.dms.preparation.domain.converters;

import jakarta.persistence.AttributeConverter;

public abstract class StringValueObjectConverter<T extends ValueObject<String>> implements AttributeConverter<T, String> {
    @Override
    public String convertToDatabaseColumn(T attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }
}
