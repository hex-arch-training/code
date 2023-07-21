package hexarch.dms.verification.application.port;

import hexarch.dms.verification.domain.DocumentRevisionId;
import hexarch.dms.verification.domain.VerificationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

public record RevisionPreviewQueryModel(String title, String content, DocumentRevisionId documentRevisionId,
                                        VerificationStatus verificationStatus) {
}
