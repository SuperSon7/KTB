package com.vani.week1;

public class ApiCallTask extends NetworkTask {
    private final String httpMethod;

    public ApiCallTask(String taskId, String targetUrl, long simulatedDuration, String httpMethod) {
        super(taskId, targetUrl, simulatedDuration);
        this.httpMethod = httpMethod;
    }

    @Override
    public String getDescription() {
        return targetUrl + "에 " + httpMethod + " API 호출";
    }

    @Override
    public void run() {
        // ImageResizeTask의 run() 메소드와 거의 동일한 구조로 구현 (메시지만 다르게)
        if (cancelled) { setStatus(TaskStatus.CANCELED); return; }
        setStatus(TaskStatus.RUNNING);
        try {
            System.out.printf("... [%s] API 호출 중...\n", getTaskId());
            Thread.sleep(simulatedDuration);
            if (cancelled) { setStatus(TaskStatus.CANCELED); return; }
            if (Math.random() < 0.1) { throw new RuntimeException("API 응답 시간 초과!"); }
            setStatus(TaskStatus.SUCCESS);
        } catch (InterruptedException e) { setStatus(TaskStatus.CANCELED); Thread.currentThread().interrupt();
        } catch (Exception e) { setStatus(TaskStatus.FAILED); setError(e); }
    }
}