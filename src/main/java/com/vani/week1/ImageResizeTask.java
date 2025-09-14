package com.vani.week1;

public class ImageResizeTask extends FileTask {

    private final int targetWidth;
    private final int targetHeight;

    public ImageResizeTask(String taskId, String filePath, long simulatedDuration, int targetWidth, int targetHeight) {
        super(taskId, filePath, simulatedDuration);
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
    }

    @Override
    public String getDescription() {
        return filePath + " 파일을 " + targetWidth + "x" + targetHeight + "로 리사이즈";
    }
    @Override
    public void run() {
        if (cancelled) {
            setStatus(TaskStatus.CANCELED);
            return;
        }
        setStatus(TaskStatus.RUNNING);

        try {
            System.out.printf("... [%s] 이미지 리사이즈 작업 중...\n", getTaskId());
            Thread.sleep(this.simulatedDuration);

            if (cancelled) {
                setStatus(TaskStatus.CANCELED);
                return;
            }

            setStatus(TaskStatus.SUCCESS);
        } catch (InterruptedException e) {
            setStatus(TaskStatus.CANCELED);
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            setStatus(TaskStatus.FAILED);
            setError(e);
        }
    }
}
