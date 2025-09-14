package com.vani.week1;

// FileUploadTask.java
public class FileUploadTask extends FileTask {
    private final String serverUrl;

    public FileUploadTask(String taskId, String filePath, long simulatedDuration, String serverUrl) {
        super(taskId, filePath, simulatedDuration);
        this.serverUrl = serverUrl;
    }

    @Override
    public String getDescription() {
        return filePath + " 파일을 " + serverUrl + "에 업로드";
    }

    @Override
    public void run() {
        if (cancelled) { setStatus(TaskStatus.CANCELED); return; }
        setStatus(TaskStatus.RUNNING);
        try {
            System.out.printf("... [%s] 파일 업로드 중...\n", getTaskId());
            Thread.sleep(simulatedDuration);
            if (cancelled) { setStatus(TaskStatus.CANCELED); return; }
            if (Math.random() < 0.1) { throw new RuntimeException("업로드 중 네트워크 연결 오류!"); }
            setStatus(TaskStatus.SUCCESS);
        } catch (InterruptedException e) { setStatus(TaskStatus.CANCELED); Thread.currentThread().interrupt();
        } catch (Exception e) { setStatus(TaskStatus.FAILED); setError(e); }
    }
}
