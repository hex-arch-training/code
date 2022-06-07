package hexarch.dms.preparation.domain;

import hexarch.dms.preparation.domain.converters.RevisionContentConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * This is an aggregate root whose main role is to keep the whole object tree consistent.
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PACKAGE) // needed by JPA/Hibernate
public class Revision {
    @Getter
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = RevisionContent.MAX_LENGTH)
    @Convert(converter = RevisionContentConverter.class)
    private RevisionContent content;

    @ManyToOne(cascade = CascadeType.ALL)
    private Document document;

    public static Revision createNew(DocumentTitle title, RevisionContent content) {
        final var revision = new Revision();
        revision.content = content;
        revision.document = Document.createNew(title);
        return revision;
    }

    public static Revision createNewAndAddToExistingDocument(Document document, RevisionContent content) {
        final var revision = new Revision();
        revision.content = content;
        revision.document = document;
        return revision;
    }
}
