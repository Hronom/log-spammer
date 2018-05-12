package com.github.hronom.logspammer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogSpammerApp implements ApplicationRunner {
    private final Log logger = LogFactory.getLog(getClass());

    public static void main(String[] args) {
        SpringApplication.run(LogSpammerApp.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 1_000; i++) {
            logger.info(String.format("Entry %d", i));
        }
    }
}