package hexarch.dms.verification.adapter.out.db;

import hexarch.dms.verification.domain.DocumentRevisionId;
import hexarch.dms.verification.domain.RevisionVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VerificationRequestRepository extends JpaRepository<RevisionVerification, Long> {

    @Query("""
        FROM RevisionVerification rv WHERE rv.documentRevisionId=:revisionId
        """)
    Optional<RevisionVerification> findByDocumentRevisionId(@Param("revisionId") DocumentRevisionId revisionId);

}
