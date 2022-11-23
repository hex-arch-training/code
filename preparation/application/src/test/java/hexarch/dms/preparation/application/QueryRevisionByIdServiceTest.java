package hexarch.dms.preparation.application;

import hexarch.dms.preparation.application.port.RevisionQueryModel;
import hexarch.dms.preparation.application.port.out.QueryRevisionPort;
import hexarch.dms.preparation.domain.DocumentTitle;
import hexarch.dms.preparation.domain.RevisionContent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QueryRevisionByIdServiceTest {

    private final Long ID = 11L;

    @Mock
    private QueryRevisionPort queryRevisionPort;

    @InjectMocks
    private QueryRevisionByIdService queryRevisionByIdService;

    @Test
    public void returnRevisionQueryModel() {
        // given
        var expectedRevisionQueryModel = Optional.of(
                new RevisionQueryModel(ID, 2L, new DocumentTitle("title"), new RevisionContent("content")));
        when(queryRevisionPort.queryById(ID)).thenReturn(expectedRevisionQueryModel);
        // when
        var revisionQueryModel = queryRevisionByIdService.queryBy(ID);
        // then
        assertEquals(revisionQueryModel, expectedRevisionQueryModel);
    }

}
