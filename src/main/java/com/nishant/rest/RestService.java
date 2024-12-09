package com.nishant.rest;


import com.nishant.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    private RestTemplate restTemplate;

    public String getData() {
        Product product = new Product();
        return restTemplate.postForObject("url", product, String.class );


       // return "hello";
    }
}
