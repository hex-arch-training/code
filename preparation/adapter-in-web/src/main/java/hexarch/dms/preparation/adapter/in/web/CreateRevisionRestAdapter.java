package hexarch.dms.preparation.adapter.in.web;

import hexarch.dms.preparation.application.port.in.CreateRevisionUseCase;
import hexarch.dms.preparation.domain.DocumentTitle;
import hexarch.dms.preparation.domain.RevisionContent;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
class CreateRevisionRestAdapter {

    private final CreateRevisionUseCase createRevisionUseCase;

    @PostMapping("/revisions")
    public ResponseEntity<String> createRevision(@RequestBody CreateRevisionRequestBody requestBody) {
        var command = new CreateRevisionUseCase.CreateRevisionCommand(
                new DocumentTitle(requestBody.documentTitle()),
                new RevisionContent(requestBody.revisionContent())
        );
        var revisionId = createRevisionUseCase.apply(command);
        return new ResponseEntity<>(String.valueOf(revisionId), HttpStatus.CREATED);
    }
}
