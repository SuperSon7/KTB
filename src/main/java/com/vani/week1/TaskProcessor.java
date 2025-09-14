package com.vani.week1;

import java.time.Duration;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.*;

import static com.vani.week1.TaskStatus.FAILED;


/**
 * Task들을 받아 멀티스레드로 처리하고, 전체과정을 관리하는 클래스
 */
public class TaskProcessor {
    private final ExecutorService executor;

    private final ConcurrentMap<String, Task>  runningTasks = new ConcurrentHashMap<>();

    private final Queue<Task> completedTasks = new ConcurrentLinkedQueue<>();

    //private final TaskResultLogger logger;

    private final ProcessorMonitor monitorThread;
    private final TimeoutManager timeoutMangerThread;

    // 생성자
    /**
     * @param poolSize : Worker 스레드 개수
     * @param timeout : 작업 시간 초과 기준
     */
    public TaskProcessor(int poolSize, long timeout) {
        this.executor = Executors.newFixedThreadPool(poolSize);

        this.monitorThread = new ProcessorMonitor(this, 3000);
        this.timeoutMangerThread = new TimeoutManager(this, timeout, 1000);

        // 로거

    }

    // submit 메서드
    /**
     * 외부(Main)에서 Task를 받아 처리 시스템에 제출합니다.
     * @param task : 실행할 Task 객체
     */
    public void submit(Task task){
        System.out.println("[" + task.getTaskId() + "] 작업 접수됨.");
        executor.submit(createTaskProcessorThread(task));
    }

    public void start() {
        System.out.println("관리 스레드 시작...");
        new Thread(monitorThread).start();
        new Thread(timeoutMangerThread).start();
    }

    public void shutdown(){
        System.out.println("시스템을 종료합니다...");

        monitorThread.shutdown();
        timeoutMangerThread.shutdown();

        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        // 로거 종료
    }

    // crateTaskProcessorThread
    /**
     * Task객채를 받아, 실행 전후 처리 로직이 포함된 Runnable 래퍼를 생성하여 반환합니다.
     */
    private Runnable createTaskProcessorThread(Task task){
        return () -> {
            try {
                task.setStartTime();
                runningTasks.put(task.getTaskId(), task);
                System.out.println("[시작] ID: " + task.getTaskId() + " | " + task.getDescription());

                task.run();

            } catch (Exception e) {
                task.setStatus(FAILED);
                task.setError(e);
            } finally {
                task.setEndTime();
                runningTasks.remove(task.getTaskId());
                completedTasks.add(task);
                printTaskResultToCli(task);
            }

        };
    }

    /**
     * 완료된 Task의 결과를 CLI에 실시간을 출력
     */
    private void printTaskResultToCli(Task task){
        synchronized (System.out) {
            String resultMessage = switch (task.getStatus()) {
                case SUCCESS -> "o 작업 성공 o";
                case FAILED -> "x 작업 실패 x - " + (task.getError() != null ? task.getError().getMessage() : "알 수 없는 오류");
                case CANCELED -> "작업 중단";
                default -> task.getStatus().toString();
            };
            Duration duration = task.getDuration();
            String durationStr = duration != null ? String.format("%.2f 초", duration.toMillis() / 1000.0) : "N/A";

            System.out.printf("[결과] ID: %s | 설명: %s | 상태: %s | 소요시간: %s\n",
                    task.getTaskId(), task.getDescription(), resultMessage, durationStr);
        }
    }
    //  Getter 들
    /**
     * 현재 '실행 중'인 작업의 개수를 반환합니다.
     * @return 실행 중인 작업의 수
     */
    public int getRunningTaskCount() {
        return this.runningTasks.size();
    }

    /**
     * 현재까지 '완료'된 작업의 개수를 반환합니다.
     * @return 완료된 작업의 수
     */
    public int getCompletedTaskCount() {
        return this.completedTasks.size();
    }

    /**
     * 아직 실행되지 않고 '대기 중'인 작업의 개수를 반환합니다.
     * ExecutorService의 내부 큐 사이즈를 확인해야 합니다.
     * @return 대기 중인 작업의 수
     */
    public int getPendingTaskCount() {
        // ExecutorService를 ThreadPoolExecutor로 형 변환해야 getQueue() 메소드를 쓸 수 있습니다.
        if (executor instanceof ThreadPoolExecutor) {
            return ((ThreadPoolExecutor) executor).getQueue().size();
        }
        return 0;
    }

    // TimeoutManager가 스캔할 수 있도록 '실행 중인 작업 목록 자체'를 반환하는 메소드
    public Map<String, Task> getRunningTasks() {
        return this.runningTasks;
    }
}
