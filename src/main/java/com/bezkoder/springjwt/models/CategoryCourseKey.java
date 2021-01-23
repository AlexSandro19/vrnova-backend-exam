package com.bezkoder.springjwt.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CategoryCourseKey implements Serializable {

    @Column(name = "category_id")
    Long categoryId;

    @Column(name = "course_id")
    Long courseId;

    public CategoryCourseKey() {
    }

    public CategoryCourseKey(Long categoryId, Long courseId) {
        this.categoryId = categoryId;
        this.courseId = courseId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryCourseKey that = (CategoryCourseKey) o;
        return Objects.equals(categoryId, that.categoryId) &&
                Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, courseId);
    }
}

