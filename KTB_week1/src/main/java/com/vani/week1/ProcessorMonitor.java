package com.vani.week1;


public class ProcessorMonitor implements Runnable {
    private final TaskProcessor taskProcessor;
    private final long checkIntervalMs;
    private volatile boolean running = true;

    public ProcessorMonitor(TaskProcessor processor, long interval) {
        this.taskProcessor = processor;
        this.checkIntervalMs = interval;
    }

    @Override
    public void run() {
        while (running) {
            try {
                int pending = taskProcessor.getPendingTaskCount();
                int running = taskProcessor.getRunningTaskCount();
                int completed = taskProcessor.getCompletedTaskCount();

                System.out.printf("[Monitor] 대기: %d | 실행 중: %d | 완료: %d\n",
                        pending, running, completed);

                Thread.sleep(checkIntervalMs);
            } catch (InterruptedException e) {
                this.running = false;
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Monitor 스레드 종료.");
    }

    public void shutdown() {
        this.running = false;
    }
}
