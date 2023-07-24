package hexarch.dms.preparation.adapter.in.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RevisionRestController {
    @PostMapping("/revision")
    public void createRevision() {
    }

    @GetMapping("/revision/{revisionId}")
    public void getRevision() {
    }

    @PostMapping("/revision/{revisionId}/requestVerification")
    public void requestVerification() {
    }
}
