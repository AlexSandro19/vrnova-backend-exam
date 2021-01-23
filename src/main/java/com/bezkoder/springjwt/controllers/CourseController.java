package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.CourseDto;
import com.bezkoder.springjwt.dto.OrderCourseDto;
import com.bezkoder.springjwt.dto.PagingHeaders;
import com.bezkoder.springjwt.dto.PagingResponse;
import com.bezkoder.springjwt.models.CategoryCourse;
import com.bezkoder.springjwt.models.Course;
import com.bezkoder.springjwt.models.Order;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.service.CategoryService;
import com.bezkoder.springjwt.service.CourseService;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/courses")
public class CourseController {

    private CourseService courseService;
    private CategoryService categoryService;

    public CourseController(CourseService courseService, CategoryService categoryService) {
        this.courseService = courseService;
        this.categoryService = categoryService;
    }

    @Transactional
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Course>> get(
            @And({
                    @Spec(path = "title", params = "title", spec = Like.class),
                    @Spec(path = "teacher", params = "teacher", spec = Equal.class),
                    @Spec(path = "price", params = {"priceMin", "priceMax"}, spec = Between.class),
                    /*@Spec(path = "createDate", params = {"createDateGt", "createDateLt"}, spec = Between.class)*/
            }) Specification<Course> spec,
            Sort sort,
            @RequestHeader HttpHeaders headers
    ){
        final PagingResponse response = courseService.get(spec, headers, sort);
        return new ResponseEntity<>(response.getElements(), returnHttpHeaders(response), HttpStatus.OK);
    }

    public HttpHeaders returnHttpHeaders(PagingResponse response) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(PagingHeaders.COUNT.getName(), String.valueOf(response.getCount()));
        headers.set(PagingHeaders.PAGE_SIZE.getName(), String.valueOf(response.getPageSize()));
        headers.set(PagingHeaders.PAGE_OFFSET.getName(), String.valueOf(response.getPageOffset()));
        headers.set(PagingHeaders.PAGE_NUMBER.getName(), String.valueOf(response.getPageNumber()));
        headers.set(PagingHeaders.PAGE_TOTAL.getName(), String.valueOf(response.getPageTotal()));
        return headers;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getProductById(@PathVariable("id") Long id) {
        Course course = courseService.findById(id);
        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setTitle(course.getTitle());
        List<CategoryCourse> categoryCourses = course.getCategoryCourseList();
        List<String> categoryNames = new ArrayList<>();
        for (CategoryCourse i : categoryCourses) {
            categoryNames.add(i.getCategory());
        }
        courseDto.setCategoryNames(categoryNames);
        courseDto.setImage(course.getImage());
        courseDto.setDescription(course.getDescription());
        courseDto.setPrice(course.getPrice());
        courseDto.setTeacher(course.getTeacher());
        //this will be caught by our ControllerExceptionHandler
        return new ResponseEntity<>(courseDto, HttpStatus.OK);
    }
}
