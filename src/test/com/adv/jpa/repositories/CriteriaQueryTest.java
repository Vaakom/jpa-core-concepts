package com.adv.jpa.repositories;

import com.adv.jpa.JpaConceptsApplication;
import com.adv.jpa.domain.Course;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaConceptsApplication.class)
public class CriteriaQueryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Test
    public void findAll() {
        CriteriaBuilder cb =  em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        Root<Course> courseRoot = cq.from(Course.class);


        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        logger.info("Criteria result -> {}", query.getResultList());
    }

    @Test
    public void findByName() {
        CriteriaBuilder cb =  em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        Root<Course> courseRoot = cq.from(Course.class);

        Predicate likeName = cb.like(courseRoot.get("name"), "%se t%");
        cq.where(likeName);

        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        logger.info("Criteria by name result -> {}", query.getResultList());
    }

    @Test
    public void join() {
        CriteriaBuilder cb =  em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        Root<Course> courseRoot = cq.from(Course.class);
        Join<Object, Object> join = courseRoot.join("studentList", JoinType.LEFT);

        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        logger.info("Criteria without student result -> {}", query.getResultList());
    }



}