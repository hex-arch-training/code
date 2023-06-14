package hexarch.dms.verification.application.port;

public record RevisionPreviewQueryModel(
        long revisionId,
        String title,
        String content,
        String status,
        String user) {
}
