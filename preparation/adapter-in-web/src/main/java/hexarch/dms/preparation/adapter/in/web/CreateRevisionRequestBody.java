package hexarch.dms.preparation.adapter.in.web;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor // needed by Jackson mapper
@Getter
@Setter
public class CreateRevisionRequestBody {
    private String documentTitle;
    private String revisionContent;
}
