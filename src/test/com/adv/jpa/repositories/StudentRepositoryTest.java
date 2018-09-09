package com.adv.jpa.repositories;

import com.adv.jpa.JpaConceptsApplication;
import com.adv.jpa.domain.Address;
import com.adv.jpa.domain.Passport;
import com.adv.jpa.domain.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaConceptsApplication.class)
public class StudentRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentRepository repository;

    @Autowired
    private EntityManager em;


    @Test
    @Transactional
    public void getStudentDetails(){
        Student student = repository.findById(2001L);
        logger.info("Strudent -> {}", student);
        logger.info("Passport -< {}", student.getPassport() );
    }

    @Test
    @Transactional
    public void setAddressDetails(){
        Student student = repository.findById(2001L);
        student.setAddress(new Address("101", "Nowhere st.", "Mistburg"));
        em.flush();
        logger.info("Strudent -> {}", student);
        logger.info("Address -> {}", student.getAddress() );
    }

    @Test
    @Transactional
    public void getPassportDetails(){
        Passport passport = em.find(Passport.class, 4001L);
        logger.info("Passport -< {}", passport );
        logger.info("Strudent -> {}", passport.getStudent());
    }

    @Test
    @Transactional
    public void getStudentAndCourses(){
        Student student = em.find(Student.class, 2001L);
        logger.info("Strudent -> {}", student);
        logger.info("Courses -> {}", student.getCourseList());
    }

}