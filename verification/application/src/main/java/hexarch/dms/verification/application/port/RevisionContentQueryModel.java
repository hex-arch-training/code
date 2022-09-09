package hexarch.dms.verification.application.port;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RevisionContentQueryModel {

    private final String title;

    private final String content;
}
