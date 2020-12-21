package ru.testing.example;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class ResultCheckerTest {


    @Test
    public void checkResultsTest() {
        ResultChecker resultChecker = new ResultChecker();

        boolean result = resultChecker.checkResults(Collections.singletonList(new Student("Иван", "Иванов", 5)));
        assertTrue(result);

        result = resultChecker.checkResults(Collections.singletonList(new Student("Иван", "Иванов", 4)));
        assertFalse(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkResultsExceptionTest() {
        ResultChecker resultChecker = new ResultChecker();
        resultChecker.checkResults(Collections.emptyList());
    }

}