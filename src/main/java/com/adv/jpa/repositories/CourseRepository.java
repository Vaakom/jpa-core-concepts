package com.adv.jpa.repositories;


import com.adv.jpa.domain.Course;
import com.adv.jpa.domain.Review;
import com.adv.jpa.domain.ReviewRaiting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CourseRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    public Course findById(Long id){
        return em.find(Course.class, id);
    }


    public Course createOrUpdate(Course course){
        if(course.getId() == null)
            em.persist(course);
        else
            em.merge(course);

        return course;
    }

    public void delete(Long id){
        Course course = findById(id);
        em.remove(course);
    }

    public void addHardcodedReviews(){
        Course course = findById(1003L);
        logger.info("course.getReviews() -> {}", course.getReviewList());

        Review review1 = new Review(ReviewRaiting.FIVE, "Handsome");
        Review review2 = new Review(ReviewRaiting.FIVE, "Magnificent");

//        course.addReview(review1);
        review1.setCourse(course);

//        course.addReview(review2);
        review2.setCourse(course);

        em.persist(review1);
        em.persist(review2);
    };

    public void addReviewsToCourse(Long courseId, List<Review> reviews){
        Course course = findById(courseId);
        logger.info("course.getReviews() -> {}", course.getReviewList());

        for(Review review : reviews){
            course.addReview(review);
            review.setCourse(course);
            em.persist(review);
        }
    };

    public void playWithEm(){
        Course course1 = new Course("Play 1");
        em.persist(course1);

        Course course2 = findById(1001L);
        course2.setName("Course1 Updated!");
        em.merge(course2);

        em.flush();
    }

}
