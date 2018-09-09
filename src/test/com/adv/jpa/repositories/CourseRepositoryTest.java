package com.adv.jpa.repositories;

import com.adv.jpa.JpaConceptsApplication;
import com.adv.jpa.domain.Course;
import com.adv.jpa.domain.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaConceptsApplication.class)
public class CourseRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CourseRepository repository;


    @Test
    @Transactional
    public void findByIdFirstLevelCash() {
        Course course1 = repository.findById((long)1002);
        logger.info("Course 1 -> {}", course1);

        Course course2 = repository.findById((long)1002);
        logger.info("Course 2 -> {}", course2);

        logger.info("Course 2 was taken from cash. No second sql call.");

        assertTrue(true);
    }

    @Test
    public void findById() {
        Course course = repository.findById((long)1002);
        assertEquals("Course two", course.getName());
    }

    @Test
    @DirtiesContext
    public void delete() {
        repository.delete((long)1002);
        Course course = repository.findById((long)1002);

        assertNull(course);
    }

    @Test
    @DirtiesContext
    public void createOrUpdateCreate() {
        Course course = repository.createOrUpdate(new Course("Course four"));
        assertNotNull(course.getId());
    }

    @Test
    @DirtiesContext
    public void createOrUpdateUpdate() {
        Course course = repository.findById((long)1003);
        course.setName("Updated!");

        course = repository.createOrUpdate(course);
        assertEquals("Updated!", course.getName());
    }

    @Test
    @DirtiesContext
    public void playWithEm() {
        repository.playWithEm();
    }

    @Test
    @Transactional
    public void getCourceReviewList(){
            Course course = repository.findById(1001L);
            logger.info("Review list -> {}", course.getReviewList());
    }

    @Test
    public void getReview(){
        Review review  = entityManager.find(Review.class, 5001L);
        logger.info("Course -> {}", review.getCourse());
    }
}