package ru.testing.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest(ResultProcessor.class)
public class ResultProcessorTest {

    @Test
    public void testWithNew() throws Exception {
        ResultFileReader fileReader = mock(ResultFileReader.class);
        PowerMockito.whenNew(ResultFileReader.class).withNoArguments().thenReturn(fileReader);
        when(fileReader.readResults(anyString())).thenReturn(Collections.emptyList());
        ResultProcessor resultProcessor = new ResultProcessor(mock(ReportWriter.class), mock(ResultRepository.class));
        List<Student> result = resultProcessor.read("path");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testWithPrivateMethod() throws Exception {
        ResultRepository repository = mock(ResultRepository.class);
        ResultProcessor resultProcessor = PowerMockito.spy(new ResultProcessor(mock(ReportWriter.class), repository));
        PowerMockito.doReturn(Collections.singletonList(new Student("Николай", "Иванов", 3))).when(resultProcessor, "readFromDefaultPath");
        resultProcessor.process();
        verify(repository).writeResultToDb(anyList());
    }

}