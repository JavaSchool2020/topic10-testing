package ru.testing.example;

import java.util.List;

public class ResultChecker {

    public boolean checkResults(List<Student> students) {
        if (students == null || students.isEmpty()) {
            throw new IllegalArgumentException("Нет оценок");
        }
        for (Student student : students) {
            if (student.getResult() != 5)
                return false;
        }
        return true;
    }
}
