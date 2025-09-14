package com.vani.week1;

public class FileTask extends Task {
    protected final String filePath;

    public FileTask(String taskId, String filePath, long SimulatedDuration) throws IllegalAccessException {
        super(taskId, simulatedDuration);
        if (filePath == null || filePath.isBlank()) {
            throw new IllegalAccessException("파일 경로가 비어있을 수 없습니다.");
        }
        this.filePath = filePath;
    }
    public String getFilePath() {
        return filePath;
    }
}
