package hexarch.dms.verification.adapter.out.db;

import hexarch.dms.verification.application.port.RevisionVerificationQueryModel;
import hexarch.dms.verification.domain.DocumentRevisionId;
import hexarch.dms.verification.domain.RevisionVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RevisionVerificationRepository extends JpaRepository<RevisionVerification, Long> {

    @Query("""
            SELECT
                NEW hexarch.dms.verification.application.port.RevisionVerificationQueryModel(rv.documentRevisionId, rv.verificationStatus)
            FROM RevisionVerification rv
            WHERE rv.documentRevisionId = :revisionId
            """)
    Optional<RevisionVerificationQueryModel> queryReadModelByRevisionId(@Param("revisionId") DocumentRevisionId revisionId);

    @Query("""
            FROM RevisionVerification rv
            WHERE rv.documentRevisionId = :revisionId
            """)
    Optional<RevisionVerification> queryByRevisionId(@Param("revisionId") DocumentRevisionId revisionId);
}
