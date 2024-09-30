package hexarch.dms.preparation.domain;

import hexarch.dms.preparation.domain.converters.DocumentTitleConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE) // needed by JPA/Hibernate
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PACKAGE)
@EqualsAndHashCode
public class Document {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = DocumentTitle.MAX_LENGTH)
    @Convert(converter = DocumentTitleConverter.class)
    private DocumentTitle title;

    static Document createNew(@NonNull DocumentTitle title) {
        var newDocument = new Document();
        newDocument.title = title;

        return newDocument;
    }
}
