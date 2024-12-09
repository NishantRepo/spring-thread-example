package com.nishant.controller;

import com.nishant.entity.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBrakerController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/test123")
    @CircuitBreaker(name = "bac", fallbackMethod = "fallbackMethod")
    public String getDetails(@RequestBody Product product) {
        restTemplate.postForObject("url", product, String.class);
        return restTemplate.getForObject("url", String.class);
    }

    public String fallbackMethod(Throwable throwable) {
        return "fallback method response";
    }
}
