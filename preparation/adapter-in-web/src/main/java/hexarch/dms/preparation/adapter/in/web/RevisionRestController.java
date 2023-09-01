package hexarch.dms.preparation.adapter.in.web;

import hexarch.dms.preparation.application.port.in.CreateRevisionCommand;
import hexarch.dms.preparation.application.port.in.CreateRevisionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RevisionRestController {

    private final CreateRevisionUseCase createRevisionUseCase;

    @PostMapping("/revision")
    public ResponseEntity<String> createRevision(@RequestBody CreateRevisionRequestBody requestBody) {
        var revisionId = createRevisionUseCase.apply(new CreateRevisionCommand(requestBody.documentTitle(), requestBody.revisionContent()));
        return new ResponseEntity<>(String.valueOf(revisionId), HttpStatus.CREATED);

    }

    @PostMapping("/revision/{revisionId}/requestVerification")
    public void requestVerification() {
    }
}
