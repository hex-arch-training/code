package hexarch.dms.preparation.adapter.in.web;

import hexarch.dms.preparation.application.port.RevisionQueryModel;
import hexarch.dms.preparation.application.port.in.CreateRevisionCommand;
import hexarch.dms.preparation.application.port.in.CreateRevisionUseCase;
import hexarch.dms.preparation.application.port.in.QueryRevisionByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class RevisionRestController {
    private final CreateRevisionUseCase createRevisionUseCase;
    private final QueryRevisionByIdUseCase queryRevisionByIdUseCase;

    @PostMapping("/revision")
    public ResponseEntity<String> createRevision(@RequestBody CreateRevisionRequestBody requestBody) {
        var revisionId = createRevisionUseCase.apply(new CreateRevisionCommand(requestBody.getDocumentTitle(), requestBody.getRevisionContent()));
        return new ResponseEntity<>(String.valueOf(revisionId), HttpStatus.CREATED);
    }

    @GetMapping("/revision/{revisionId}")
    public RevisionQueryModel getRevision(@PathVariable Long revisionId) {
        return queryRevisionByIdUseCase.queryBy(revisionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
