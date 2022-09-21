package hexarch.dms.preparation.domain;

import hexarch.dms.preparation.domain.converters.RevisionContentConverter;
import hexarch.dms.preparation.domain.converters.RevisionStatusConverter;
import hexarch.dms.shared.util.VisibleForTesting;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * This is an aggregate root whose main role is to keep the whole object tree consistent.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE) // needed by JPA/Hibernate
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PACKAGE)
@EqualsAndHashCode
public class Revision {
    @Getter
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = RevisionContent.MAX_LENGTH)
    @Convert(converter = RevisionContentConverter.class)
    private RevisionContent content;

    @Column(nullable = false)
    @Convert(converter = RevisionStatusConverter.class)
    private RevisionStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    private Document document;

    public void lock() {
        if (status != RevisionStatus.EDITABLE) {
            throw new RevisionNotEditableException(id, status);
        }
        status = RevisionStatus.LOCKED;
    }

    public static Revision createNew(DocumentTitle title, RevisionContent content) {
        final var revision = new Revision();
        revision.content = content;
        revision.status = RevisionStatus.EDITABLE;
        revision.document = Document.createNew(title);
        return revision;
    }

    @VisibleForTesting
    public static Revision createExisting(long id, DocumentTitle title, RevisionContent revisionContent, RevisionStatus status) {
        return Revision.builder()
                .id(id)
                .content(revisionContent)
                .status(status)
                .document(Document.builder().title(title).build())
                .build();
    }

    public static Revision createNewAndAddToExistingDocument(Document document, RevisionContent content) {
        final var revision = new Revision();
        revision.content = content;

        revision.status = RevisionStatus.EDITABLE;
        revision.document = document;
        return revision;
    }
}
