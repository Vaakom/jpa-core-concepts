package com.adv.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Course")
@NamedQueries(value = {
        @NamedQuery(name = "get_all_courses", query = "select c from Course c"),
        @NamedQuery(name = "get_all_courses_with_join_fetch", query = "select c from Course c join fetch c.studentList s join fetch s.passport p"),
        @NamedQuery(name = "get_cou_courses", query = "select c from Course c where c.name like 'Cou%'")
})
@Where(clause = "is_deleted=false")
@SQLDelete(sql = "update course set is_deleted=true where id = ?")
public class Course {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "course")
    private List<Review> reviewList = new ArrayList<>();

    @ManyToMany(mappedBy = "courseList")
    @JsonIgnore
    private List<Student> studentList = new ArrayList<>();

    @UpdateTimestamp
    private LocalDateTime updated;

    @CreationTimestamp
    private LocalDateTime created;

    private boolean isDeleted;

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    @PreRemove
    private void preRemove(){
        this.isDeleted = true;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void addReview(Review review){
        reviewList.add(review);
    }

    public void removeReview(Review review){
        reviewList.remove(review);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void addStudent(Student student) {
        this.studentList.add(student);
    }

    public void removeStudent(Student student){
        this.studentList.remove(student);
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
