package ru.testing.example;

import java.util.List;

public interface ResultRepository {
    boolean writeResultToDb(List<Student> students);
}
