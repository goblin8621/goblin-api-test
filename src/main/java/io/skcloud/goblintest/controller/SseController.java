package io.skcloud.goblintest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
public class SseController {

    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> sse() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> "Server Time: " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

}
