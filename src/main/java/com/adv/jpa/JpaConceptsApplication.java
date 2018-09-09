package com.adv.jpa;

import com.adv.jpa.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaConceptsApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(JpaConceptsApplication.class);

    @Autowired
    private EmployeeRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(JpaConceptsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("*** RUN APPLICATION RUN METHOD ***");
    }
}
