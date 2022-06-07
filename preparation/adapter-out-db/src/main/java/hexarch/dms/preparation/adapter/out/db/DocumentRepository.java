package hexarch.dms.preparation.adapter.out.db;

import hexarch.dms.preparation.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
