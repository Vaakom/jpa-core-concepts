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

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaConceptsApplication.class)
public class PerformanceTuningTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    public void creatingNPlusOneProblem() {
        List<Course> list = em.createNamedQuery("get_all_courses", Course.class).getResultList();

        for(Course course : list)
            logger.info("Course -> {} Students -> {}", course, course.getStudentList());
    }

    @Test
    @Transactional
    public void solvingNPlusOneProblemByEntityGraph() {
        EntityGraph<Course> graph = em.createEntityGraph(Course.class);
        graph.addSubgraph("studentList").addSubgraph("passport");

        List<Course> list = em.createNamedQuery("get_all_courses", Course.class).
                setHint("javax.persistence.loadgraph", graph).
                getResultList();

        for(Course course : list)
            logger.info("Course -> {} Students -> {}", course, course.getStudentList());
    }

    @Test
    @Transactional
    public void solvingNPlusOneProblemByJoinFetch() {
        List<Course> list = em.createNamedQuery("get_all_courses_with_join_fetch", Course.class).getResultList();

        for(Course course : list)
            logger.info("Course -> {} Students -> {}", course, course.getStudentList());
    }

}