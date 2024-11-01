package io.skcloud.goblintest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class WebsocketController {

    private static final Logger logger = LoggerFactory.getLogger(WebsocketController.class);

    @GetMapping("/message")
    public String receiveMessage(@RequestParam(value = "content") String content) {
        logger.info("Received message: {}", content);
        return "Message received: " + content;
    }
}
