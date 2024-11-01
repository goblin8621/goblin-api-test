package io.skcloud.goblintest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class AsyncController {

    @GetMapping("/async")
    public Mono<ResponseDto> asyncCall() {
        LocalDateTime requestTime = LocalDateTime.now(); // 요청 시간 기록

        return Mono.just(new ResponseDto("Processing request asynchronously", requestTime, null, null))
                .delayElement(Duration.ofSeconds(5)) // 5초 지연
                .map(responseDto -> {
                    LocalDateTime responseTime = LocalDateTime.now(); // 응답 시간 기록
                    long processingTime = Duration.between(requestTime, responseTime).toMillis(); // 처리 시간 계산

                    // 응답 데이터 설정
                    responseDto.setResponseMessage("Async response after delay");
                    responseDto.setResponseTime(responseTime);
                    responseDto.setProcessingTime(processingTime);
                    return responseDto;
                });
    }

    // 응답 데이터 구조 정의
    static class ResponseDto {
        private String responseMessage;
        private LocalDateTime requestTime;
        private LocalDateTime responseTime;
        private Long processingTime; // 밀리초 단위 처리 시간

        public ResponseDto(String responseMessage, LocalDateTime requestTime, LocalDateTime responseTime, Long processingTime) {
            this.responseMessage = responseMessage;
            this.requestTime = requestTime;
            this.responseTime = responseTime;
            this.processingTime = processingTime;
        }

        // Getters & Setters
        public String getResponseMessage() {
            return responseMessage;
        }

        public void setResponseMessage(String responseMessage) {
            this.responseMessage = responseMessage;
        }

        public LocalDateTime getRequestTime() {
            return requestTime;
        }

        public void setRequestTime(LocalDateTime requestTime) {
            this.requestTime = requestTime;
        }

        public LocalDateTime getResponseTime() {
            return responseTime;
        }

        public void setResponseTime(LocalDateTime responseTime) {
            this.responseTime = responseTime;
        }

        public Long getProcessingTime() {
            return processingTime;
        }

        public void setProcessingTime(Long processingTime) {
            this.processingTime = processingTime;
        }
    }
}
