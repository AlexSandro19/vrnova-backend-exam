package com.bezkoder.springjwt.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name="Category")
@Table(name = "category")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<CategoryCourse> categoryCourseList;

    public Category() {
    }

    public Category(Long id, String name, List<CategoryCourse> categoryCourseList) {
        this.id = id;
        this.name = name;
        this.categoryCourseList = categoryCourseList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryCourse> getCategoryCourseList() {
        return categoryCourseList;
    }

    public void setCategoryCourseList(List<CategoryCourse> categoryCourseList) {
        this.categoryCourseList = categoryCourseList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(name, category.name) &&
                Objects.equals(categoryCourseList, category.categoryCourseList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, categoryCourseList);
    }
}

