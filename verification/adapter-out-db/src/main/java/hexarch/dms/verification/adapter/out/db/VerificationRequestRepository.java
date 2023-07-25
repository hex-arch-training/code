package hexarch.dms.verification.adapter.out.db;

import java.util.Optional;

import hexarch.dms.verification.domain.DocumentRevisionId;
import hexarch.dms.verification.domain.RevisionVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VerificationRequestRepository extends JpaRepository<RevisionVerification, Long> {

    @Query("FROM RevisionVerification req WHERE req.documentRevisionId = :revisionId")
    Optional<RevisionVerification> findByRevisionId(@Param("revisionId") DocumentRevisionId revisionId);
}
