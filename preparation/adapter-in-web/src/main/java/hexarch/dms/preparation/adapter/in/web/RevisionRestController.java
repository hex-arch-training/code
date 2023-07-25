package hexarch.dms.preparation.adapter.in.web;

import hexarch.dms.preparation.application.port.RevisionQueryModel;
import hexarch.dms.preparation.application.port.in.QueryRevisionByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class RevisionRestController {

    private final QueryRevisionByIdUseCase queryRevisionByIdUseCase;

    @PostMapping("/revision")
    public void createRevision() {
    }

    @GetMapping("/revision/{revisionId}")
    public RevisionQueryModel getRevision(@PathVariable Long revisionId) {
        return queryRevisionByIdUseCase.queryBy(revisionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/revision/{revisionId}/requestVerification")
    public void requestVerification() {
    }
}
