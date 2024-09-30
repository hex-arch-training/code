package hexarch.dms.verification.domain.converters;

import hexarch.dms.verification.domain.DocumentRevisionId;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class DocumentRevisionIdConverter implements AttributeConverter<DocumentRevisionId, Long> {
    @Override
    public Long convertToDatabaseColumn(DocumentRevisionId attribute) {
        return attribute.getRevisionId();
    }

    @Override
    public DocumentRevisionId convertToEntityAttribute(Long dbData) {
        return new DocumentRevisionId(dbData);
    }
}
