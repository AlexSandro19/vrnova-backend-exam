package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.dto.PagingHeaders;
import com.bezkoder.springjwt.dto.PagingResponse;
import com.bezkoder.springjwt.exception.ResourceNotFoundException;
import com.bezkoder.springjwt.models.Course;
import com.bezkoder.springjwt.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public List<Course> findAll(){
        return courseRepository.findAll();
    }

    public Course findById(Long id){
        return courseRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }

    public PagingResponse get(Specification<Course> spec, HttpHeaders headers, Sort sort) {
        if (isRequestPaged(headers)) {
            return get(spec, buildPageRequest(headers, sort));
        } else {
            List<Course> entities = get(spec, sort);
            return new PagingResponse((long) entities.size(), 0L, 0L, 0L, 0L, entities);
        }
    }

    private boolean isRequestPaged(HttpHeaders headers) {
        return headers.containsKey(PagingHeaders.PAGE_NUMBER.getName()) && headers.containsKey(PagingHeaders.PAGE_SIZE.getName());
    }

    private Pageable buildPageRequest(HttpHeaders headers, Sort sort) {
        int page = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_NUMBER.getName())).get(0));
        int size = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_SIZE.getName())).get(0));
        return PageRequest.of(page, size, sort);
    }

    public PagingResponse get(Specification<Course> spec, Pageable pageable) {
        Page<Course> page = courseRepository.findAll(spec, pageable);
        List<Course> content = page.getContent();
        return new PagingResponse(page.getTotalElements(), (long) page.getNumber(),
                (long) page.getNumberOfElements(), pageable.getOffset(),
                (long) page.getTotalPages(), content);
    }

    public List<Course> get(Specification<Course> spec, Sort sort) {
        return courseRepository.findAll(spec, sort);
    }

}


