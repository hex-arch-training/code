package hexarch.dms.preparation.application;

public class RevisionNotFoundException extends RuntimeException {
    public RevisionNotFoundException(long revisionId) {
        super(String.format("Revision %d not found.", revisionId));
    }
}
