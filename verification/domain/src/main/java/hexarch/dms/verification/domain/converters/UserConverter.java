package hexarch.dms.verification.domain.converters;

import hexarch.dms.verification.domain.User;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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
