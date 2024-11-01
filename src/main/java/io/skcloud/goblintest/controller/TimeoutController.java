package io.skcloud.goblintest.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TimeoutController {
    @RequestMapping("/timeout/{timeout}")
    public String timeout(@PathVariable("timeout") long timeout) throws InterruptedException {
        Thread.sleep(timeout);
        return "timeout : "+timeout;
    }

}
