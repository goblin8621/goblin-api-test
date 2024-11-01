package io.skcloud.goblintest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/status")
public class StatusController {

    @GetMapping("/{statuscode}")
    public ResponseEntity<Void> getStatus(@PathVariable int statuscode) {
        // HttpStatus enum에서 상태 코드를 찾고, 없으면 500을 반환
        HttpStatus status = HttpStatus.resolve(statuscode);
        if (status != null) {
            return new ResponseEntity<>(status);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private int totalCalls = 0; // 전체 호출 횟수
    private List<Boolean> responseSequence = new ArrayList<>(); // 성공/실패 시퀀스 리스트
    private int currentThreshold = 0; // 현재 설정된 실패 비율

    @GetMapping("/failure/{threshold}")
    public ResponseEntity<Void> getFixedFailureStatus(@PathVariable int threshold) {

        // 새로운 threshold 값이 들어오면 카운터와 시퀀스 리스트를 초기화
        if (threshold != currentThreshold || responseSequence.isEmpty()) {
            currentThreshold = threshold;
            totalCalls = 0;
            responseSequence = generateRandomResponseSequence(threshold);
        }

        totalCalls++;

        // 실패할 경우 503 반환, 성공할 경우 200 반환
        boolean isFailure = responseSequence.get(totalCalls - 1);
        if (isFailure) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE); // 503
        } else {
            return new ResponseEntity<>(HttpStatus.OK); // 200
        }
    }

    // 실패 비율에 맞는 랜덤 시퀀스를 생성
    private List<Boolean> generateRandomResponseSequence(int failurePercentage) {
        List<Boolean> sequence = new ArrayList<>();
        int totalRequests = 100; // 100번 기준
        int failures = (totalRequests * failurePercentage) / 100;
        int successes = totalRequests - failures;

        // 실패를 true, 성공을 false로 리스트에 추가
        for (int i = 0; i < failures; i++) {
            sequence.add(true); // 실패
        }
        for (int i = 0; i < successes; i++) {
            sequence.add(false); // 성공
        }

        // 리스트를 랜덤하게 섞음
        Collections.shuffle(sequence);
        return sequence;
    }
}
