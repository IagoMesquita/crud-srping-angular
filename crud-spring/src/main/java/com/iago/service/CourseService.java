package com.iago.service;

import com.iago.model.Course;
import com.iago.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.iago.exception.RecordNotFoundException;

@Validated
@Service
public class CourseService {

  private final CourseRepository courseRepository;


  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public List<Course> list() {
    return courseRepository.findAll();
  }

  public Course findById(@NotNull @Positive Long id) {
    return courseRepository.findById(id)
        .orElseThrow(() -> new RecordNotFoundException(id));
  }

  public Course create(@Valid Course course) {
    return courseRepository.save(course);
  }

  public Course update(@NotNull @Positive Long id, @Valid Course course) {
    return courseRepository.findById(id)
        .map(recordFound -> {
          recordFound.setName(course.getName());
          recordFound.setCategory(course.getCategory());
          return courseRepository.save(recordFound);
        }).orElseThrow(() -> new RecordNotFoundException(id));
  }

  public void delete(@NotNull @Positive Long id) {
    Course courseDb = findById(id);
    courseRepository.delete(courseDb);
  }

}
