package hexarch.dms.preparation.domain.converters;

import hexarch.dms.preparation.domain.RevisionStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RevisionStatusConverter implements AttributeConverter<RevisionStatus, String> {
    @Override
    public String convertToDatabaseColumn(RevisionStatus attribute) {
        return attribute.getDbValue();
    }

    @Override
    public RevisionStatus convertToEntityAttribute(String dbData) {
        return RevisionStatus.fromDb(dbData);
    }
}
