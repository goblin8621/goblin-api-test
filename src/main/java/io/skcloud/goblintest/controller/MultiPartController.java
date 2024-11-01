package io.skcloud.goblintest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MultiPartController {
    @PostMapping("/mf")
    public void mf(MultipartFile multipartFile){
        System.out.println(multipartFile);
    }
}
