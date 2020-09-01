package org.juanitodread.demo.services;

import org.juanitodread.demo.dtos.Health;
import org.juanitodread.demo.repositories.HealthRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class HealthService {
    private final Logger logger = LoggerFactory.getLogger(HealthService.class);

    private final HealthRepository healthRepository;

    @Autowired
    public HealthService(HealthRepository healthRepository) {
        this.healthRepository = healthRepository;
    }

    @Async("taskExecutor")
    public CompletableFuture<Health> asyncHealth() {

        return CompletableFuture.completedFuture(health());
    }

    public Health health() {
        logger.info("Getting the service health: repository={}", this.healthRepository);
        var result = this.healthRepository.getHealth();
        var health = new Health(
                Integer.parseInt(result.getOrDefault("code", "0000")),
                result.getOrDefault("status", "Bad"),
                result.getOrDefault("message", "")
        );
        logger.info("Service health completed");
        return health;
    }
}
