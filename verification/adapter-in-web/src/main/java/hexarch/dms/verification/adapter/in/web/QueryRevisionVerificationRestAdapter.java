package hexarch.dms.verification.adapter.in.web;

import hexarch.dms.verification.application.port.RevisionVerificationQueryModel;
import hexarch.dms.verification.application.port.in.QueryRevisionVerificationUseCase;
import hexarch.dms.verification.domain.DocumentRevisionId;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@AllArgsConstructor
class QueryRevisionVerificationRestAdapter {

    private final QueryRevisionVerificationUseCase useCase;

    @GetMapping("/verifications/{revisionId}")
    RevisionVerificationQueryModel getRevisionVerification(@PathVariable Long revisionId) {
        return useCase.queryBy(new DocumentRevisionId(revisionId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
