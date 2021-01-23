package com.bezkoder.springjwt;

import com.bezkoder.springjwt.models.Course;
import com.bezkoder.springjwt.repository.CourseRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CourseRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CourseRepository courseRepository;

    @BeforeEach
    public void init() {
        courseRepository.deleteAll();
    }

    @Test
    public void should_find_no_courses_if_repository_is_empty() {
        Iterable<Course> courses = courseRepository.findAll();
        assertThat(courses).isEmpty();
    }

    @Test
    public void should_save_a_course() {
        Course course = new Course();
        Course savedCourse = courseRepository.save(course);
        assertEquals(savedCourse, course);
    }

    @Test
    public void should_find_all_courses() {
        Course c1 = new Course();
        entityManager.persist(c1);

        Course c2 = new Course();
        entityManager.persist(c2);

        Course c3 = new Course();
        entityManager.persist(c3);

        Iterable<Course> products = courseRepository.findAll();

        assertThat(products).hasSize(3).contains(c1, c2, c3);
    }

    @Test
    public void should_find_course_by_id() {
        Course course1 = new Course();
        entityManager.persist(course1);

        Course course2 = new Course();
        entityManager.persist(course2);

        Course foundCourse = courseRepository.findById(course2.getId()).get();

        assertThat(foundCourse).isEqualTo(course2);
    }

    @Test
    public void should_delete_course_by_id() {
        Course c1 = new Course();
        entityManager.persist(c1);

        Course c2 = new Course();
        entityManager.persist(c2);

        Course c3 = new Course();
        entityManager.persist(c3);

        courseRepository.deleteById(c2.getId());

        Iterable<Course> courses = courseRepository.findAll();

        assertThat(courses).hasSize(2).contains(c1, c3);
    }

    @Test
    public void should_delete_all_courses() {
        entityManager.persist(new Course());
        entityManager.persist(new Course());

        courseRepository.deleteAll();

        assertThat(courseRepository.findAll()).isEmpty();
    }
}
