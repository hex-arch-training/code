package hexarch.dms.verification.adapter.in.web;

import hexarch.dms.verification.application.port.in.AcceptRevisionUseCase;
import hexarch.dms.verification.domain.DocumentRevisionId;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
class RevisionVerificationRestController {

    private final AcceptRevisionUseCase acceptRevisionUseCase;

    @PostMapping("/revisions/{revisionId}/accept")
    public ResponseEntity<Void> createVerificationRequest(@PathVariable Long revisionId) {
        acceptRevisionUseCase.apply(new AcceptRevisionUseCase.AcceptRevisionCommand(new DocumentRevisionId(revisionId)));
        return ResponseEntity.ok().build();
    }
}
