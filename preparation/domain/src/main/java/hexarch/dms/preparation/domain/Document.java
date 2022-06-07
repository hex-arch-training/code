package hexarch.dms.preparation.domain;

import hexarch.dms.preparation.domain.converters.DocumentTitleConverter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PACKAGE) // needed by JPA/Hibernate
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
