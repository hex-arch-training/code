package hexarch.dms.preparation.domain.converters;

import hexarch.dms.preparation.domain.RevisionStatus;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

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
