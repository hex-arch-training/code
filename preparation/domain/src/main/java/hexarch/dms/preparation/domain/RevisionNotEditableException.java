package hexarch.dms.preparation.domain;

public class RevisionNotEditableException extends RuntimeException {
    public RevisionNotEditableException(long revisionId, RevisionStatus status) {
        super(String.format("Cannot perform on revision %d because it is not editable (current status is %s)", revisionId, status.name()));
    }
}
