package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.CategoryCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryCourseRepository extends JpaRepository<CategoryCourse, Long> {
}
