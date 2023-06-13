package hexarch.dms.preparation.adapter.out.db;

import hexarch.dms.preparation.application.port.in.RevisionQueryModel;
import hexarch.dms.preparation.domain.Revision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface RevisionRepository extends JpaRepository<Revision, Long> {

    @Query("""
        SELECT 
            new hexarch.dms.preparation.application.port.in.RevisionQueryModel(rev.id, doc.id, doc.title, rev.content)
        FROM Revision rev
        JOIN rev.document doc
        WHERE rev.id = :id
    """)
    Optional<RevisionQueryModel> queryById(@Param("id") long id);
}
