package hexarch.dms.verification.adapter.in.web;

import hexarch.dms.verification.domain.VerificationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record RevisionPreviewBody(String title, String content, VerificationStatus verificationStatus) {

}
