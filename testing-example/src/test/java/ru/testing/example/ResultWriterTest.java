package ru.testing.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ResultWriterTest {

    @Mock
    private ReportWriter reportWriter;
    @Mock
    private ResultRepository resultRepository;

    @Test
    public void writeResult() {
        ResultWriter resultWriter = new ResultWriter(reportWriter, resultRepository);
        resultWriter.writeResult(Collections.singletonList(new Student("Иван", "Иванов", 5)));
        verify(resultRepository).writeResultToDb(anyList());
        verify(reportWriter, never()).writeReport(anyList());
    }

    @Test
    public void writeResultToOtherOtherDbTest() {
        ResultWriter resultWriter = new ResultWriter(reportWriter, resultRepository);
        ResultRepository otherRepository = mock(ResultRepository.class);
        when(resultRepository.writeResultToDb(anyList())).thenReturn(false);

        resultWriter.writeResultToOtherOtherDb(Collections.singletonList(new Student("Иван", "Иванов", 5)), otherRepository);
        verify(otherRepository).writeResultToDb(anyList());
    }
}