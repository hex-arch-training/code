package hexarch.dms.preparation.adapter.in.web;

import hexarch.dms.preparation.application.port.RevisionQueryModel;
import hexarch.dms.preparation.application.port.in.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class RevisionRestController {

    private final QueryRevisionByIdUseCase queryRevisionByIdUseCase;

    private final CreateRevisionUseCase createRevisionUseCase;

    private final RequestVerificationUseCase requestVerificationUseCase;

    @PostMapping("/revision")
    public ResponseEntity<String> createRevision(@RequestBody CreateRevisionRequestBody requestBody) {
        var revisionId = createRevisionUseCase.apply(new CreateRevisionCommand(requestBody.documentTitle(), requestBody.revisionContent()));
        return new ResponseEntity<>(String.valueOf(revisionId), HttpStatus.CREATED);

    }

    @GetMapping("/revision/{revisionId}")
    public RevisionQueryModel getRevision(@PathVariable("revisionId") Long revisionId) {
        return queryRevisionByIdUseCase.queryBy(revisionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/revision/{revisionId}/requestVerification")
    public void requestVerification(@PathVariable("revisionId") Long revisionId) {
        requestVerificationUseCase.apply(
                new RequestVerificationCommand(revisionId)
        );
    }
}
