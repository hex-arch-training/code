package hexarch.dms.preparation.adapter.in.web;

import hexarch.dms.preparation.application.port.in.QueryRevisionByIdUseCase;
import hexarch.dms.preparation.application.port.in.RevisionQueryModel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@AllArgsConstructor
class RevisionRestController {

    private final QueryRevisionByIdUseCase queryRevisionByIdUseCase;

    @GetMapping("/revisions/{revisionId}")
    public RevisionQueryModel getRevision(@PathVariable Long revisionId) {
        return queryRevisionByIdUseCase.queryBy(revisionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
