package com.vani.week1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

public class TimeoutManager implements Runnable {
    private final TaskProcessor taskProcessor;
    private final long timeoutMs;
    private final long checkIntervalMs;
    private volatile boolean running = true;

    public TimeoutManager(TaskProcessor processor, long timeoutMs, long checkIntervalMs) {
        this.taskProcessor = processor;
        this.timeoutMs = timeoutMs;
        this.checkIntervalMs = checkIntervalMs;
    }

    @Override
    public void run() {
        while (running) {
            try {

                Map<String, Task> runningTasks = taskProcessor.getRunningTasks();

                for (Task task : runningTasks.values()) {
                    if (task.getStartTime() != null) {
                        long runningTime = Duration.between(task.getStartTime(), LocalDateTime.now()).toMillis();
                        if (runningTime > timeoutMs) {
                            System.out.printf("[TimeoutManager] Task %s 작업이 시간(%dms)을 초과했습니다. 중단 요청!\n",
                                    task.getTaskId(), timeoutMs);
                            task.cancel();
                        }
                    }
                }
                Thread.sleep(checkIntervalMs);
            } catch (InterruptedException e) {
                this.running = false;
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("TimeoutManager 스레드 종료.");
    }

    public void shutdown() {
        this.running = false;
    }
}