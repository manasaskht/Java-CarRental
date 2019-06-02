package com.group4.carrental.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Home {

    @GetMapping()
    public String getHome(){
        return "Hello World";
    }

}
