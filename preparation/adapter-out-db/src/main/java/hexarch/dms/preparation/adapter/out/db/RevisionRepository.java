package hexarch.dms.preparation.adapter.out.db;

import hexarch.dms.preparation.domain.Revision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevisionRepository extends JpaRepository<Revision, Long> {

}
