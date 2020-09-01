package org.juanitodread.demo.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Map;

@Repository
public class HealthRepository {
    private final Logger logger = LoggerFactory.getLogger(HealthRepository.class);

    public Map<String, String> getHealth() {
        logger.info("Starting a query for health");

        Map<String, String> healthRow = Map.of(
                "code", "0001",
                "status", "OK",
                "message", "Everything is OK"
        );
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("Query executed");
        return healthRow;
    }
}
