package com.nishant.controller;


import com.nishant.entity.Product;
import com.nishant.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestTestController {

    @Autowired
    private TestService testService;

    @PostMapping("/test")
    public String testMethod(@RequestBody Product product) {

        return "hello";
    }
}
