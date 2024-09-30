package hexarch.dms.verification.adapter.in.web;

import hexarch.dms.verification.application.port.in.AcceptRevisionCommand;
import hexarch.dms.verification.application.port.in.AcceptRevisionUseCase;
import hexarch.dms.verification.application.port.in.QueryRevisionPreviewUseCase;
import hexarch.dms.verification.domain.DocumentRevisionId;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@AllArgsConstructor
public class RevisionVerificationRestController {

    private final AcceptRevisionUseCase acceptRevisionUseCase;
    private final QueryRevisionPreviewUseCase queryRevisionPreviewUseCase;

    @PostMapping("/revision/{revisionId}/accept")
    public ResponseEntity<Void> createVerificationRequest(@PathVariable("revisionId") Long revisionId) {
        acceptRevisionUseCase.apply(new AcceptRevisionCommand(new DocumentRevisionId(revisionId)));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/revision/{revisionId}/preview")
    public RevisionPreviewBody preview(@PathVariable("revisionId") Long revisionId) {
        return queryRevisionPreviewUseCase
                .queryBy(new DocumentRevisionId(revisionId))
                .map(queryModel -> new RevisionPreviewBody(
                        queryModel.title(),
                        queryModel.content(),
                        queryModel.verificationStatus()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
