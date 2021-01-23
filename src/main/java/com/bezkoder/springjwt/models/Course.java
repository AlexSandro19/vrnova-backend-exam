package com.bezkoder.springjwt.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity(name="Course")
@Table(name = "course")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "description", length=1000)
    private String description;

    @Column(name = "teacher")
    private String teacher;

    @Column(name = "price")
    private Double price;

    @Column(name = "duration")
    private String duration;

    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Video> videos = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<CategoryCourse> categoryCourseList;

    public Course() {
    }

    public Course(Long id, String title, String image, String description, String teacher, Double price,
                  String duration, List<Video> videos, List<CategoryCourse> categoryCourseList) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.description = description;
        this.teacher = teacher;
        this.price = price;
        this.duration = duration;
        this.videos = videos;
        this.categoryCourseList = categoryCourseList;
    }

    public List<String> getCategoryNames(){
        return getCategoryCourseList().stream().map(CategoryCourse::getCategory).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<CategoryCourse> getCategoryCourseList() {
        return categoryCourseList;
    }

    public void setCategoryCourseList(List<CategoryCourse> categoryCourseList) {
        this.categoryCourseList = categoryCourseList;
    }

    @JsonIgnore
    public List<Video> getVideos() {
        return videos;
    }

    public List<String> getVideoUrls() {
        return this.getVideos().stream().map(Video::getVideoUrl).collect(Collectors.toList());
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(title, course.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
