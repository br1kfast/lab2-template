package com.lab2.reservacationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class NewController {
    @Autowired
    RestTemplate restTemplate;

    @PatchMapping("/get/{username}")
    public String getDiscount(@PathVariable("username") String username){
        String status = restTemplate.postForObject("http://localhost:8082/api/v1/updateStatus/{username}", username, String.class, username);
        return status;
    }

}
