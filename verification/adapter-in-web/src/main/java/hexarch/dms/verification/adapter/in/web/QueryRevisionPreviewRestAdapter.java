package hexarch.dms.verification.adapter.in.web;

import hexarch.dms.verification.application.port.RevisionPreviewQueryModel;
import hexarch.dms.verification.application.port.in.QueryRevisionPreviewUseCase;
import hexarch.dms.verification.domain.DocumentRevisionId;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@AllArgsConstructor
public class QueryRevisionPreviewRestAdapter {

    private final QueryRevisionPreviewUseCase useCase;

    @GetMapping("/verifications/{revisionId}/preview")
    public RevisionPreviewQueryModel getPreview(@PathVariable Long revisionId) {
        return useCase.queryFor(new DocumentRevisionId(revisionId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
