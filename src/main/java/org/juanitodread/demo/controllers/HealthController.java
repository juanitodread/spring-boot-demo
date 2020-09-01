package org.juanitodread.demo.controllers;

import org.juanitodread.demo.dtos.Health;
import org.juanitodread.demo.services.HealthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/health")
public class HealthController {
    private final Logger logger = LoggerFactory.getLogger(HealthController.class);

    private final HealthService healthService;

    @Autowired
    public HealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    @RequestMapping(value = "/async-ping")
    public ResponseEntity<Health> asyncHealth() throws ExecutionException, InterruptedException {
        logger.info("Receiving a request");

        var start = System.currentTimeMillis();
        var healthResponseFuture = this.healthService.asyncHealth();

        CompletableFuture.allOf(healthResponseFuture);
        var healthResponse = healthResponseFuture.get();

        logger.info("****************** Request latency: {} ms", System.currentTimeMillis() - start);

        return ResponseEntity.ok().body(healthResponse);
    }

    @RequestMapping(value = "/ping")
    public ResponseEntity<Health> health() {
        logger.info("Receiving a request");

        var start = System.currentTimeMillis();
        var healthResponse = this.healthService.health();

        logger.info("****************** Request latency: {} ms", System.currentTimeMillis() - start);

        return ResponseEntity.ok().body(healthResponse);
    }
}
