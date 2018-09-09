package com.adv.jpa.repositories;

import com.adv.jpa.JpaConceptsApplication;
import com.adv.jpa.domain.Course;
import com.adv.jpa.domain.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaConceptsApplication.class)
public class JpqlTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Test
    public void findByName() {
        List<Course> list = em.createNamedQuery("get_cou_courses", Course.class).getResultList();
        logger.info("Find by name -> {}", list);
    }


    @Test
    public void findAll() {
        List<Course> list = em.createNamedQuery("get_all_courses", Course.class).getResultList();
        logger.info("Find all -> {}", list);
    }

    @Test
    public void coursesWithNoStudents() {
        List<Course> list = em.createQuery("select c from Course c where c.studentList is empty", Course.class).getResultList();
        logger.info("Courses without students -> {}", list);
    }

    @Test
    public void coursesMoreThanOneStudent() {
        List<Course> list = em.createQuery("select c from Course c where size(c.studentList) >= 2", Course.class).getResultList();
        logger.info("Courses with more than one student -> {}", list);
    }

    @Test
    public void coursesOrderedByNumberOfStudents() {
        List<Course> list = em.createQuery("select c from Course c order by size(c.studentList)", Course.class).getResultList();
        logger.info("Courses ordered -> {}", list);
    }

    @Test
    public void studentsByPassportPattern() {
        List<Student> list = em.createQuery("select s from Student s where s.passport.number like '%1%'", Student.class).getResultList();
        logger.info("Students by passport pattern -> {}", list);
    }

    @Test
    public void leftJoinStudentCouse() {
        List list = em.createQuery("select c, s from Course c left join c.studentList s").getResultList();
        logger.info("result size -> {}", list.size());
    }

    @Test
    @DirtiesContext
    public void createOrUpdateCreate() {
//        Course course = repository.createOrUpdate(new Course("Course four"));
//        assertNotNull(course.getId());
    }

    @Test
    @DirtiesContext
    public void createOrUpdateUpdate() {
//        Course course = repository.findById((long)1003);
//        course.setName("Updated!");
//
//        course = repository.createOrUpdate(course);
//        assertEquals("Updated!", course.getName());
    }

    @Test
    @DirtiesContext
    public void playWithEm() {
//        repository.playWithEm();
    }
}