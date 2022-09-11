package com.devdouglas.dsmeta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home() {
        return "API Sales business. \n\n Click <a href=\"/swagger-ui.html\">here</a> to see the documentation.";
    }
}
