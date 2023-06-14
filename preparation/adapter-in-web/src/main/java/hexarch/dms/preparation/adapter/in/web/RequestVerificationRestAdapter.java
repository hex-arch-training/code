package hexarch.dms.preparation.adapter.in.web;

import hexarch.dms.preparation.application.port.in.RequestVerificationUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RequestVerificationRestAdapter {

    private final RequestVerificationUseCase requestVerificationUseCase;

    @PostMapping("/revisions/{revisionId}/requestVerification")
    public ResponseEntity<Void> requestVerification(@PathVariable Long revisionId) {
        requestVerificationUseCase.apply(new RequestVerificationUseCase.RequestVerificationCommand(revisionId));
        return ResponseEntity.ok().build();
    }
}
