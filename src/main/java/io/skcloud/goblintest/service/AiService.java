package io.skcloud.goblintest.service;

import io.skcloud.goblintest.model.AiRequest;
import io.skcloud.goblintest.model.ImageGenerationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class AiService {
    private static final String OPENAI_URL = "https://api.openai.com/v1/engines/davinci-codex/completions";
    private static final String API_KEY = "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"; // 여기에 API 키를 입력합니다


    private static final String NAI_URL = "https://api.novelai.net/ai/generate-image";
    private static final String ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6InNwV0xOLXIzd3JfYkM5cFJOWE40WSIsIm5jIjoiYkt2VnU4Mi11VDJxdU4wYXUxUlhyIiwiaWF0IjoxNzA1MDIwNDM4LCJleHAiOjE3MDc2MTI0Mzh9.9_i9i_ey8Tvm0arSIVeJp_qz43aXsO9ACe-DZBExyZw";

    @Autowired
    private WebClient webClient;

    public Mono<String> chat(AiRequest aiRequest) {

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("prompt", aiRequest.getPrompt());
        requestBody.put("max_tokens", 5);
        Mono<String> responseMono = webClient.post()
                .uri(OPENAI_URL)
                .header("Authorization", "Bearer " + API_KEY)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class);

           return responseMono;

    }

    public Mono<String> imageGen(ImageGenerationRequest imageGenerationRequest) {

        Mono<String> responseMono = webClient.post()
                .uri(NAI_URL)
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(imageGenerationRequest)
                .retrieve()
                .bodyToMono(String.class);

        return responseMono;

    }
}
