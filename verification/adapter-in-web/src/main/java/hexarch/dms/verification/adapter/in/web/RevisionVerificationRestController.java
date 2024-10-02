package hexarch.dms.verification.adapter.in.web;

import hexarch.dms.verification.application.QueryRevisionPreviewService;
import hexarch.dms.verification.application.port.RevisionPreviewQueryModel;
import hexarch.dms.verification.application.port.in.AcceptRevisionCommand;
import hexarch.dms.verification.application.port.in.AcceptRevisionUseCase;
import hexarch.dms.verification.application.port.in.QueryRevisionPreviewUseCase;
import hexarch.dms.verification.domain.DocumentRevisionId;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RevisionVerificationRestController {

    private final AcceptRevisionUseCase acceptRevisionUseCase;

    private final QueryRevisionPreviewUseCase queryRevisionPreviewUseCase;

    @PostMapping("/revision/{revisionId}/accept")
    public void createVerificationRequest(@PathVariable("revisionId") Long revisionId) {
        acceptRevisionUseCase.apply(new AcceptRevisionCommand(new DocumentRevisionId(revisionId)));
    }

    @GetMapping("/revision/{revisionId}/preview")
    public RevisionPreviewQueryModel preview(@PathVariable("revisionId") Long revisionId) {
        return queryRevisionPreviewUseCase.queryBy(new DocumentRevisionId(revisionId));
    }
}
