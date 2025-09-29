package com.vani.week1;

import java.time.Duration;
import java.time.LocalDateTime;

public class Task implements Runnable {
    private final String taskId;
    protected final long simulatedDuration;
    protected TaskStatus status;
    protected volatile boolean cancelled = false;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Exception error;

    public Task(String taskId, long simulatedDuration) {
        this.taskId = taskId;
        this.simulatedDuration = simulatedDuration;
        this.status = TaskStatus.PENDING;
    }

    public String getDescription() {
        return String.format("Task ID: %s", this.taskId);

    }
    @Override
    public void run() {};


    public void cancel() {
        this.cancelled = true;
        if (this.status == TaskStatus.RUNNING || this.status == TaskStatus.PENDING) {
            this.status = TaskStatus.CANCELED;
        }
        System.out.println("중단 요청: [" + taskId + "]");
    }

    // Getter
    public String getTaskId() { return taskId; }
    public TaskStatus getStatus() { return status; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public Exception getError() { return error; }
    public boolean isCancelled() { return cancelled; }
    public Duration getDuration() {
        if (startTime != null && endTime != null) {
            return Duration.between(startTime, endTime);
        }
        return null;
    }
    protected void setStatus(TaskStatus status) { this.status = status; }
    public synchronized void setStartTime() { this.startTime = LocalDateTime.now(); }
    public synchronized void setEndTime() { this.endTime = LocalDateTime.now(); }
    protected synchronized void setError(Exception error) { this.error = error; }
}

