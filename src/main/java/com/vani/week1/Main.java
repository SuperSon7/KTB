package com.vani.week1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("===== 백그라운드 작업 처리기 =====");

            // 테크스 종류 선택
            System.out.println(("1. 이미지 리사이즈 작업"));
            System.out.println(("2. 파일 업로드 작업"));
            System.out.println(("3. API 호출 작업 "));
            System.out.println(("실행할 작업을 선택하세요 (1-3): "));
            int taskType = scanner.nextInt();

            // 공통 설정 값 입력받기
            System.out.println(("생성할 작업 개수를 입력하세요: "));
            int taskCount = scanner.nextInt();

            System.out.println(("최소 실행 시간(ms)을 입력하세요: "));
            long minExecutionMs = scanner.nextInt();

            System.out.println(("최대 실행 시간(ms)을 입력하세요: "));
            long maxExecutionMs = scanner.nextInt();

            System.out.println(("시간 초과 기준(ms)을 입력하세요: "));
            int timeout = scanner.nextInt();

            scanner.nextLine(); // 버퍼 비우기

            // 선택한 Task 생성
            List<Task> tasks = new ArrayList<>();
            System.out.println(taskCount + "개의 작업을 생성합니다...");
            for (int i = 0; i < taskCount; i++) {
                long randomDuration = ThreadLocalRandom.current().nextLong(minExecutionMs, maxExecutionMs + 1);
                String taskIdSuffix = String.format("%03d", i);

                Task task = switch (taskType) {
                    case 1 -> new ImageResizeTask("Resize-" + taskIdSuffix, "/temp/image" + i + ".jpg", randomDuration, 1920, 1080);
                    case 2 -> new FileUploadTask("Upload-" + taskIdSuffix, "/temp/archive" + i + ".zip", randomDuration, "ftp://myserver.com/uploads");
                    case 3 -> new ApiCallTask("API-" + taskIdSuffix, "https://api.example.com/data/" + i, randomDuration, "GET");
                    default -> {
                        System.out.println("잘못된 선택입니다. 기본 작업(이미지 리사이즈)을 생성합니다.");
                        yield new ImageResizeTask("Resize-" + taskIdSuffix, "/temp/image" + i + ".jpg", randomDuration, 1920, 1080);
                    }
                };
                tasks.add(task);
            }
            // TaskProcessor 생성 및 실행
            TaskProcessor processor = new TaskProcessor(4, timeout);
            processor.start();

            // 작업 제출 및 종료 대기
            System.out.println("\n총 " + tasks.size() + "개의 작업을 제출합니다.");
            tasks.forEach(processor::submit);

            System.out.println("엔터 키를 누르면 프로그램을 종료합니다...");
            scanner.nextLine();

            processor.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}