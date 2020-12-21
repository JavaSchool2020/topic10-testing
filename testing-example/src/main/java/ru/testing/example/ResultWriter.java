package ru.testing.example;

import java.util.List;

public class ResultWriter {
    private ReportWriter reportWriter;
    private ResultRepository resultRepository;

    public ResultWriter() {
    }

    public ResultWriter(ReportWriter reportWriter, ResultRepository resultRepository) {
        this.reportWriter = reportWriter;
        this.resultRepository = resultRepository;
    }

    public void writeResult(List<Student> students) {
        if (students.size() > 3) {
            reportWriter.writeReport(students);
        } else {
            resultRepository.writeResultToDb(students);
        }
    }

    public boolean writeResultToOtherOtherDb(List<Student> students, ResultRepository otherResultRepository) {
        try {
            if (students.size() > 3) {
                reportWriter.writeReport(students);
            } else {
                if (!resultRepository.writeResultToDb(students)) {
                    otherResultRepository.writeResultToDb(students);
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
