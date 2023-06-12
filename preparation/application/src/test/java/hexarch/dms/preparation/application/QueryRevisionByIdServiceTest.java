package hexarch.dms.preparation.application;

import hexarch.dms.preparation.application.port.RevisionQueryModel;
import hexarch.dms.preparation.application.port.out.QueryRevisionPort;
import hexarch.dms.preparation.domain.DocumentTitle;
import hexarch.dms.preparation.domain.RevisionContent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QueryRevisionByIdServiceTest {

    @Mock
    QueryRevisionPort queryRevisionPort;

    @Test
    void shouldReturnRevisionQueryModelWhenValidIdProvided() {

        // Given
        var mockRevisionId = 1L;
        var mockRevisionQueryModel = new RevisionQueryModel(
                1L, 1L, new DocumentTitle("Test Title"), new RevisionContent("Test Content"));
        when(queryRevisionPort.queryBy(mockRevisionId)).thenReturn(Optional.of(mockRevisionQueryModel));

        // When
        var service = new QueryRevisionByIdService(queryRevisionPort);
        var result = service.queryBy(mockRevisionId);

        // Then
        assertEquals(Optional.of(mockRevisionQueryModel), result);
        verify(queryRevisionPort, times(1)).queryBy(mockRevisionId);
    }

    @Test
    void shouldReturnEmptyOptionalWhenInvalidIdProvided() {

        // Given
        var invalidId = -1L;
        when(queryRevisionPort.queryBy(invalidId)).thenReturn(Optional.empty());

        // When
        var service = new QueryRevisionByIdService(queryRevisionPort);
        var result = service.queryBy(invalidId);

        // Then
        assertTrue(result.isEmpty());
        verify(queryRevisionPort, times(1)).queryBy(invalidId);
    }
}
