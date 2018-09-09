package com.adv.jpa.repositories;

import com.adv.jpa.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "/courses")
public interface CourseJPARepository extends JpaRepository<Course, Long> {
}
