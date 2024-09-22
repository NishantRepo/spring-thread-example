package com.nishant.controller;

import com.nishant.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestController {


    @GetMapping("api/v1/getId/{id}")
    public String getValue(@PathVariable int id) {
        return "studnet id is: " + id;
    }

    @PostMapping(value = "api/v1/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String saveData(@RequestBody User user) {
        return "studnet";

    }

}
