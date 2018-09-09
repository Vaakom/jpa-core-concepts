package com.adv.jpa.domain;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReviewRaiting rating;

    private String description;

    @ManyToOne
    private Course course;

    public Review() {
    }

    public Review(ReviewRaiting rating, String description) {
        this.rating = rating;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rating='" + rating + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public ReviewRaiting getRating() {
        return rating;
    }

    public void setRating(ReviewRaiting rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
