package hexarch.dms.preparation.adapter.out.db;

import hexarch.dms.preparation.application.port.RevisionQueryModel;
import hexarch.dms.preparation.domain.Revision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RevisionRepository extends JpaRepository<Revision, Long> {
    @Query("SELECT new hexarch.dms.preparation.application.port.RevisionQueryModel(rev.id, doc.id, doc.title, rev.content) " +
            "FROM Revision rev " +
            "JOIN rev.document doc " +
            "WHERE rev.id = :id")
    List<RevisionQueryModel> queryById(@Param("id") Long id);
}
