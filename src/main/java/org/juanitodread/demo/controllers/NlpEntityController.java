package org.juanitodread.demo.controllers;

import org.juanitodread.demo.dtos.Health;
import org.juanitodread.demo.dtos.NlpEntity;
import org.juanitodread.demo.services.NlpEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/entities")
public class NlpEntityController {
    private final Logger logger = LoggerFactory.getLogger(NlpEntityController.class);

    private final NlpEntityService entityService;

    @Autowired
    public NlpEntityController(NlpEntityService entityService) {
        this.entityService = entityService;
    }

    @RequestMapping(value = "/async-all")
    public ResponseEntity<List<NlpEntity>> asyncAll() throws ExecutionException, InterruptedException {
        logger.info("Receiving a request");

        var start = System.currentTimeMillis();
        var entityResponseFuture = this.entityService.asyncGetAllEntities();

        CompletableFuture.allOf(entityResponseFuture);
        var entities = entityResponseFuture.get();

        logger.info("****************** Request latency: {} ms", System.currentTimeMillis() - start);

        return ResponseEntity.ok().body(entities);
    }

    @RequestMapping(value = "/all")
    public ResponseEntity<List<NlpEntity>> all() {
        logger.info("Receiving a request");

        var start = System.currentTimeMillis();
        var entities = this.entityService.getAllEntities();

        logger.info("****************** Request latency: {} ms", System.currentTimeMillis() - start);

        return ResponseEntity.ok().body(entities);
    }
}
