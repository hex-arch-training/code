package hexarch.dms.preparation.application;

public class RevisionNotFoundException extends RuntimeException {
    public RevisionNotFoundException(long revisionId) {
        super("Revision %d does not exist".formatted(revisionId));
    }
}
