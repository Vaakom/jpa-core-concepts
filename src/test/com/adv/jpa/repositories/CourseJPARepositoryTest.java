package com.adv.jpa.repositories;

import com.adv.jpa.JpaConceptsApplication;
import com.adv.jpa.domain.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaConceptsApplication.class)
public class CourseJPARepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private CourseJPARepository repository;


    @Test
    public void findByIdGood() {
        assertTrue(repository.findById(1002L).isPresent());
    }

    @Test
    public void findByIdBad() {
        assertFalse(repository.findById(3004L).isPresent());
    }

    @Test
    public void sort() {
        Sort sort = new Sort(Sort.Direction.ASC, "name");
        logger.info("Courses -> {}", repository.findAll(sort));
    }

    @Test
    public void pagination() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Course> firstPage = repository.findAll(pageRequest);
        logger.info("First page -> {}", firstPage.getContent());

        Pageable secondPageable = firstPage.nextPageable();
        Page<Course> secondPage = repository.findAll(secondPageable);
        logger.info("Second page -> {}", secondPage.getContent());
    }

}