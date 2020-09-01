package org.juanitodread.demo.services;

import org.juanitodread.demo.dtos.Health;
import org.juanitodread.demo.dtos.NlpEntity;
import org.juanitodread.demo.repositories.HealthRepository;
import org.juanitodread.demo.repositories.NlpEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class NlpEntityService {
    private final Logger logger = LoggerFactory.getLogger(NlpEntityService.class);

    private final NlpEntityRepository nlpEntityRepository;

    @Autowired
    public NlpEntityService(NlpEntityRepository nlpEntityRepository) {
        this.nlpEntityRepository = nlpEntityRepository;
    }

    @Async("taskExecutor")
    public CompletableFuture<List<NlpEntity>> asyncGetAllEntities() {
        return CompletableFuture.completedFuture(getAllEntities());
    }

    public List<NlpEntity> getAllEntities() {
        logger.info("Getting the service entities: repository={}", this.nlpEntityRepository);

        var entities = this.nlpEntityRepository.findAll();

        logger.info("Service entities completed");
        return entities;
    }
}
