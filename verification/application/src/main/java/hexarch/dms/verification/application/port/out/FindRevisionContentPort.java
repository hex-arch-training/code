package hexarch.dms.verification.application.port.out;

import hexarch.dms.verification.domain.DocumentRevisionId;

import java.util.Optional;

public interface FindRevisionContentPort {

    Optional<RevisionContent> findById(DocumentRevisionId revisionId);

    record RevisionContent(String title, String content) {}
}
