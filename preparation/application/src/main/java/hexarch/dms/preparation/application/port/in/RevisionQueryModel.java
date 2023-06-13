package hexarch.dms.preparation.application.port.in;

public record RevisionQueryModel(
        long id,
        long documentId,
        String documentTitle,
        String revisionContent
) {
}
