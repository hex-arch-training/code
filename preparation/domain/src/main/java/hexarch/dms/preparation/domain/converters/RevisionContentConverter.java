package hexarch.dms.preparation.domain.converters;

import hexarch.dms.preparation.domain.RevisionContent;

import javax.persistence.Converter;

@Converter
public class RevisionContentConverter extends StringValueObjectConverter<RevisionContent> {
    @Override
    public RevisionContent convertToEntityAttribute(String dbData) {
        return new RevisionContent(dbData);
    }
}
