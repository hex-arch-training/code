package hexarch.dms.verification.domain.converters;

import hexarch.dms.verification.domain.User;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserConverter implements AttributeConverter<User, String> {
    @Override
    public String convertToDatabaseColumn(User attribute) {
        return attribute.getLogin();
    }

    @Override
    public User convertToEntityAttribute(String dbData) {
        return new User(dbData);
    }
}
