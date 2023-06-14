package hexarch.dms.verification.application.port;

public record RevisionPreviewQueryModel(
        long revisionId,
        String title,
        String content,
        String acceptanceStatus,
        String createdBy) {
}
