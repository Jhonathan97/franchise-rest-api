package com.jhonathan.franchise_rest_api.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/")
    public String root() { return "OK"; }

    @GetMapping("/health")
    public String health() { return "OK"; }
}
