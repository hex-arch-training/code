package hexarch.dms.preparation.domain.converters;

import hexarch.dms.preparation.domain.RevisionStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RevisionStatusConverter implements AttributeConverter<RevisionStatus, Boolean> {
    @Override
    public Boolean convertToDatabaseColumn(RevisionStatus attribute) {
        return attribute == RevisionStatus.LOCKED;
    }

    @Override
    public RevisionStatus convertToEntityAttribute(Boolean dbData) {
        if (dbData) {
            return RevisionStatus.LOCKED;
        } else {
            return RevisionStatus.EDITABLE;
        }
    }
}
