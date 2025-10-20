package com.leon.challenge5.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @ApiResponse(description = "HI")
    @GetMapping("/")
    public String welcomeServer() {
        return "el leon";
    }

    @ApiResponse(description = "HI2")
    @GetMapping("/ora")
    public String welcomdServer() {
        return "hola mundo";
    }



}
