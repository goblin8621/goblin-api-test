package io.skcloud.goblintest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@EnableAsync
@RestController
public class AsyncLoadTestController {

    @Autowired
    private WebClient webClient;

    @GetMapping(value = "/load-test", produces = "text/plain;charset=UTF-8")
    public Mono<String> startLoadTest(@RequestParam String rawUrl, @RequestParam int intervalMs, @RequestParam int callCount) {
        return Flux.fromStream(IntStream.range(0, callCount).boxed())
                .flatMap(i -> Mono.delay(java.time.Duration.ofMillis(i * intervalMs))
                        .flatMap(delay -> asyncApiCall(rawUrl).map(status -> "Call " + (i + 1) + " : " + status)))
                .collectList()
                .map(results -> String.join("\n", results));
    }

    @GetMapping(value = "/load-test-concurrent", produces = "text/plain;charset=UTF-8")
    public Mono<String> startLoadTestConcurrent(@RequestParam String rawUrl, @RequestParam int intervalMs, @RequestParam int concurrentCount, @RequestParam int callCount) {
        return Flux.fromStream(IntStream.range(0, callCount).boxed())
                .flatMap(i -> Mono.delay(java.time.Duration.ofMillis(i * intervalMs))
                        .flatMap(delay -> asyncApiCall(rawUrl).map(status -> "Call " + (i + 1) + " : " + status)), concurrentCount)
                .collectList()
                .map(results -> String.join("\n", results));
    }

    @GetMapping(value = "/timed-load-test", produces = "text/plain;charset=UTF-8")
    public Mono<String> startTimedLoadTest(@RequestParam String rawUrl, @RequestParam int intervalMs, @RequestParam int durationMs) {
        long endTime = System.currentTimeMillis() + durationMs;
        return Flux.interval(java.time.Duration.ofMillis(intervalMs))
                .takeUntil(tick -> System.currentTimeMillis() >= endTime)
                .flatMap(tick -> asyncApiCall(rawUrl).map(status -> "Call " + (tick + 1) + " : " + status))
                .collectList()
                .map(results -> String.join("\n", results));
    }

    public Mono<String> asyncApiCall(String rawUrl) {
        String url = java.net.URLEncoder.encode(rawUrl, java.nio.charset.StandardCharsets.UTF_8);
        return webClient
                .get()
                .uri(java.net.URLDecoder.decode(url, java.nio.charset.StandardCharsets.UTF_8))
                .retrieve()
                .toBodilessEntity()
                .map(response -> response.getStatusCode().is2xxSuccessful() ? "200 success" : response.getStatusCode().toString().toLowerCase().replace("_", " "))
                .onErrorResume(WebClientResponseException.class, e -> {
                    return Mono.just(e.getRawStatusCode() + " " + e.getStatusText().toLowerCase().replace("_", " "));
                });
    }

        // Example cURL commands for each API
    /*
    1. Load Test API:
       curl -X GET "http://localhost:8080/load-test?url=http://example.com&intervalMs=1000&callCount=10"

    2. Load Test Concurrent API:
       curl -X GET "http://localhost:8080/load-test-concurrent?url=http://example.com&intervalMs=1000&concurrentCount=5&callCount=20"

    3. Timed Load Test API:
       curl -X GET "http://localhost:8080/timed-load-test?url=http://example.com&intervalMs=1000&durationMs=60000"
    */
        }