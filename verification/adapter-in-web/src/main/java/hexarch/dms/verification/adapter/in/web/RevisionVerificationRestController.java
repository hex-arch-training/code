package hexarch.dms.verification.adapter.in.web;

import hexarch.dms.verification.application.port.in.AcceptRevisionCommand;
import hexarch.dms.verification.application.port.in.AcceptRevisionUseCase;
import hexarch.dms.verification.domain.DocumentRevisionId;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RevisionVerificationRestController {

    private final AcceptRevisionUseCase acceptRevisionUseCase;

    @PostMapping("/revision/{revisionId}/accept")
    public ResponseEntity<Void> createVerificationRequest(@PathVariable Long revisionId) {
        acceptRevisionUseCase.apply(new AcceptRevisionCommand(new DocumentRevisionId(revisionId)));
        return ResponseEntity.ok().build();
    }
}