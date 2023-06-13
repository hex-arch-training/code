package hexarch.dms.preparation.application;

import hexarch.dms.preparation.application.port.RevisionQueryModel;
import hexarch.dms.preparation.application.port.out.QueryRevisionPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QueryRevisionByIdServiceTest {

    @Mock
    private QueryRevisionPort queryRevisionPort;

    @InjectMocks
    private QueryRevisionByIdService testedObject;

    @Test
    void shouldReturnRevisionQueryModelWhenValidIdIsProvided() {
        // given
        var revisionId = 1L;
        var queryModel = new RevisionQueryModel(revisionId, 2L, "Test title", "Test content");
        when(queryRevisionPort.queryBy(revisionId)).thenReturn(Optional.of(queryModel));

        // when
        var result = testedObject.queryBy(revisionId);

        // then
        assertTrue(result.isPresent());
        assertEquals(queryModel, result.get());
        verify(queryRevisionPort, times(1)).queryBy(revisionId);
    }
}