package com.vani.week1;

public class NetworkTask extends Task {
    protected final String targetUrl;

    public NetworkTask(String taskId, String targetUrl, long simulatedDuration) {
        super(taskId, simulatedDuration);
        if (targetUrl == null || targetUrl.isBlank()) {
            throw new IllegalAccessException("URL이 비어있을 수 없습니다.");
        }
        this.targetUrl = targetUrl;
    }

    public String getTargetUrl() {
        return targetUrl;
    }
}
