package hexarch.dms.verification.adapter.in.web;

import hexarch.dms.verification.domain.VerificationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor // needed by Jackson mapper
@AllArgsConstructor
@Getter
@Setter
public class RevisionPreviewBody {

    private String title;

    private String content;

    private VerificationStatus verificationStatus;
}
