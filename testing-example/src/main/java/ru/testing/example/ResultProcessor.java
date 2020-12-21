package ru.testing.example;

import java.util.List;

public class ResultProcessor {
    private ReportWriter reportWriter;
    private ResultRepository resultRepository;

    public ResultProcessor(ReportWriter reportWriter, ResultRepository resultRepository) {
        this.reportWriter = reportWriter;
        this.resultRepository = resultRepository;
    }

    public List<Student> read(String path) {
        ResultFileReader resultFileReader = new ResultFileReader();
        return resultFileReader.readResults(path);
    }

    public void process() {
        List<Student> results = readFromDefaultPath();
        if (countResult(results) > 3) {
            reportWriter.writeReport(results);
        } else {
            resultRepository.writeResultToDb(results);
        }
    }

    private long countResult(List<Student> students) {
        return students.stream().filter(s -> s.getResult() > 2).count();
    }

    private List<Student> readFromDefaultPath() {
        ResultFileReader resultFileReader = new ResultFileReader();
        return resultFileReader.readResultsFromDefaultPath();
    }
}
