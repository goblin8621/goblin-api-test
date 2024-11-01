package io.skcloud.goblintest.controller;

import io.skcloud.goblintest.model.AiRequest;
import io.skcloud.goblintest.model.ImageGenerationRequest;
import io.skcloud.goblintest.service.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/openAi")
public class AiController {
    @Autowired
    private AiService aiService;

    @PostMapping("/chat")
    public Mono<String> chat(@Validated @RequestBody AiRequest aiRequest) {
        return aiService.chat(aiRequest);
    }


    @PostMapping("/imageGen")
    public Mono<String> imageGen (@Validated @RequestBody ImageGenerationRequest imageGenerationRequest) {
        return aiService.imageGen(imageGenerationRequest);
    }
}
