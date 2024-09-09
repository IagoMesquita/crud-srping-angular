package com.iago.controller;

import com.iago.model.Course;
import com.iago.repository.CourseRepository;
import com.iago.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {

  private final CourseService courseService;

  @Autowired
  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @GetMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public List<Course> list() {
    return courseService.list();

  }

  @GetMapping("/{id}")
  public ResponseEntity<Course> findById(@PathVariable @NotNull @Positive Long id) {
    return ResponseEntity
        .ok()
        .body(courseService.findById(id));

  }

  @PostMapping
//  @ResponseStatus(code = HttpStatus.CREATED)
  public ResponseEntity<Course> create(@RequestBody @Valid Course course) {
    Course newCourse = courseService.create(course);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(newCourse);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Course> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Course course) {
    return ResponseEntity.ok().body(
        courseService.update(id, course)
    );


  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable @NotNull @Positive Long id) {
      courseService.delete(id);
  }
}

