package hexarch.dms.verification.adapter.in.web;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RevisionVerificationRestController {

    @PostMapping("/revision/{revisionId}/accept")
    public void createVerificationRequest() {

    }

    @GetMapping("/revision/{revisionId}/preview")
    public void preview() {

    }
}
