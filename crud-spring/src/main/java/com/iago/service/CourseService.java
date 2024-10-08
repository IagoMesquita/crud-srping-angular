package com.iago.service;

import com.iago.dto.CourseDTO;
import com.iago.dto.CoursePageDTO;
import com.iago.dto.mapper.CourseMapper;
import com.iago.model.Course;
import com.iago.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.iago.exception.RecordNotFoundException;

@Validated
@Service
public class CourseService {

  private final CourseRepository courseRepository;
  private final CourseMapper courseMapper;


  public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
    this.courseRepository = courseRepository;
    this.courseMapper = courseMapper;
  }

//  public List<CourseDTO> list() {
//    List<Course> coursesDb = courseRepository.findAll();
//    return coursesDb
//        .stream()
//        .map(courseMapper::toDTO)
//        .toList();
//
//  }

  public CoursePageDTO list(int pageNumber, int pageSize) {
    Page<Course> page = courseRepository.findAll(PageRequest.of(pageNumber, pageSize));
    List<CourseDTO> courses = page.get().map(courseMapper::toDTO).toList();
    return new CoursePageDTO(courses, page.getTotalElements(), page.getTotalPages());
  }

  public CourseDTO findById(@NotNull @Positive Long id) {
    return courseRepository.findById(id).map(courseMapper::toDTO)
        .orElseThrow(() -> new RecordNotFoundException(id));
  }

  public CourseDTO create(@Valid Course course) {
    return courseMapper.toDTO(courseRepository.save(course));
  }

  public CourseDTO update(@NotNull @Positive Long id, @Valid Course course) {
    return courseRepository.findById(id)
        .map(recordFound -> {
          recordFound.setName(course.getName());
          recordFound.setCategory(course.getCategory());
//          recordFound.setLessons(course.getLessons());
          recordFound.getLessons().clear();
//          course.getLessons().forEach(lesson -> recordFound.getLessons().add(lesson));
          recordFound.setLessons(course.getLessons());
          return courseMapper.toDTO(courseRepository.save(recordFound));
        }).orElseThrow(() -> new RecordNotFoundException(id));
  }

  public void delete(@NotNull @Positive Long id) {
    Course courseDb = courseRepository.findById(id)
        .orElseThrow(() -> new RecordNotFoundException(id));
    courseRepository.delete(courseDb);
  }

}
