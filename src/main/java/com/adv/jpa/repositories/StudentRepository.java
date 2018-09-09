package com.adv.jpa.repositories;


import com.adv.jpa.domain.Course;
import com.adv.jpa.domain.Passport;
import com.adv.jpa.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class StudentRepository {

    @Autowired
    private EntityManager em;

    public Student findById(Long id){
        return em.find(Student.class, id);
    }


    public Student createOrUpdate(Student student){
        if(student.getId() == null)
            em.persist(student);
        else
            em.merge(student);

        return student;
    }

    public void delete(Long id){
        Student student = findById(id);
        em.remove(student);
    }

    public void saveStudentPassport(){
        Passport passport = new Passport();
        passport.setNumber("G1234325");
        em.persist(passport);

        Student student = new Student();
        student.setName("Ms Adelaida");
        student.setPassport(passport);

        em.persist(student);
    }

    public void insertStudentAndCourse(Student student, Course course){

        em.persist(student);
        em.persist(course);

        course.addStudent(student);
        student.addCourse(course);

        em.persist(student);
    }
//    public void playWithEm(){
//        Student student1 = new Student("Play 1");
//        em.persist(student1);
//
//        Student student2 = findById(1001L);
//        student2.setName("Student1 Updated!");
//        em.merge(student2);
//
//        em.flush();
//    }

}
