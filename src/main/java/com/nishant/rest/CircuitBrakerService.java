package com.nishant.rest;


import com.nishant.entity.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CircuitBrakerService {

    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "serviceA", fallbackMethod = "fallback")
    public String getEmployee(Product product) {

       return restTemplate.postForObject("url", product, String.class);
    }

    public String fallback(Throwable e) {
        System.out.println("inside fallback method");
        return "service is down";
    }
}
