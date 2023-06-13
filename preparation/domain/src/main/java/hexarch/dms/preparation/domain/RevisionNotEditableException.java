package hexarch.dms.preparation.domain;

public class RevisionNotEditableException extends RuntimeException {
    public RevisionNotEditableException(Long revisionId, RevisionStatus status) {
        super("Cannot perform on revision %d because it is not editable (current status is %s)".formatted(revisionId, status.name()));
    }
}
