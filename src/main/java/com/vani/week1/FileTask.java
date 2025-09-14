package com.vani.week1;

public class FileTask extends Task {
    protected final String filePath;

    public FileTask(String taskId, String filePath, long simulatedDurationMs) {
        super(taskId, simulatedDurationMs);
        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("파일 경로가 비어있습니다.");
        }
        this.filePath = filePath;
    }
    public String getFilePath() {
        return filePath;
    }
}
