package com.bezkoder.springjwt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CategoryCourse {

    @JsonIgnore
    @EmbeddedId
    private CategoryCourseKey id;

    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonIgnore
    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    public CategoryCourse() {
    }

    public CategoryCourse(CategoryCourseKey id, Category category, Course course) {
        this.id = id;
        this.category = category;
        this.course = course;
    }

    public CategoryCourseKey getId() {
        return id;
    }

    public void setId(CategoryCourseKey id) {
        this.id = id;
    }

    public String getCategory() {
        return category.getName();
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryCourse that = (CategoryCourse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(category, that.category) &&
                Objects.equals(course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, course);
    }
}
