package com.jhonathan.franchise_rest_api.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {
    @GetMapping("/")
    public ResponseEntity<?> root() {
        Map<String,String> body = new HashMap<>();
        body.put("message","OK");
        return ResponseEntity.ok(body);
    }

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        Map<String,String> body = new HashMap<>();
        body.put("message","OK");
        return ResponseEntity.ok(body);
    }
}
