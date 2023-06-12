package hexarch.dms.preparation.adapter.in.web;

import hexarch.dms.preparation.application.port.RevisionQueryModel;
import hexarch.dms.preparation.application.port.in.CreateRevisionUseCase;
import hexarch.dms.preparation.application.port.in.QueryRevisionByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
class RevisionRestController {

    private final QueryRevisionByIdUseCase queryRevisionByIdUseCase;
    private final CreateRevisionUseCase createRevisionUseCase;

    @GetMapping("/revisions/{revisionId}")
    public RevisionQueryModel getRevision(@PathVariable Long revisionId) {
        return queryRevisionByIdUseCase.queryBy(revisionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/revisions")
    public ResponseEntity<String> createRevision(@RequestBody CreateRevisionRequestBody requestBody) {
        var revisionId = createRevisionUseCase.apply(new CreateRevisionUseCase.CreateRevisionCommand(requestBody.documentTitle(), requestBody.revisionContent()));
        return new ResponseEntity<>(String.valueOf(revisionId), HttpStatus.CREATED);
    }
}
