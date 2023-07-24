package hexarch.dms.verification.adapter.out.db;

import hexarch.dms.verification.domain.RevisionVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRequestRepository extends JpaRepository<RevisionVerification, Long> {

}
