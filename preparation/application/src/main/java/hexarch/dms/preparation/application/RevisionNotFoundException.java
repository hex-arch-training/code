package hexarch.dms.preparation.application;

public class RevisionNotFoundException extends RuntimeException {
    public RevisionNotFoundException(final long revisionId) {
        super("Revision %d cannot be found.".formatted(revisionId));
    }
}
