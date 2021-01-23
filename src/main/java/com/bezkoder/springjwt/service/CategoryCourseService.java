package com.bezkoder.springjwt.service;
import com.bezkoder.springjwt.exception.ResourceNotFoundException;
import com.bezkoder.springjwt.models.CategoryCourse;
import com.bezkoder.springjwt.repository.CategoryCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryCourseService {

    @Autowired
    CategoryCourseRepository categoryCourseRepository;

    public List<CategoryCourse> findAll(){
        return categoryCourseRepository.findAll();
    }

    public CategoryCourse findById(Long id){
        return categoryCourseRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course Category not found"));
    }
}
