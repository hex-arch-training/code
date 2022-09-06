package hexarch.dms.verification.application.port;

import hexarch.dms.verification.domain.DocumentRevisionId;
import hexarch.dms.verification.domain.VerificationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RevisionPreviewQueryModel {

    private final String title;

    private final String content;

    private final DocumentRevisionId documentRevisionId;

    private final VerificationStatus verificationStatus;
}
