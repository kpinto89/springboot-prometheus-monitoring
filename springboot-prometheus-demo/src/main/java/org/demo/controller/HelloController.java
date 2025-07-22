package org.demo.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final Counter helloCounter;

    public HelloController(MeterRegistry registry) {
        this.helloCounter = registry.counter("custom_hello_requests_total");
    }

    @GetMapping("/hello")
    public String hello() {
        helloCounter.increment();
        return "Hello, Prometheus!";
    }
}
