package hexarch.dms.preparation.domain.converters;

import hexarch.dms.preparation.domain.DocumentTitle;

import jakarta.persistence.Converter;

@Converter
public class DocumentTitleConverter extends StringValueObjectConverter<DocumentTitle> {
    @Override
    public DocumentTitle convertToEntityAttribute(String dbData) {
        return new DocumentTitle(dbData);
    }
}
