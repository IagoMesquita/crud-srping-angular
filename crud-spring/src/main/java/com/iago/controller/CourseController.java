package com.iago.controller;

import com.iago.dto.CourseDTO;
import com.iago.dto.CoursePageDTO;
import com.iago.dto.mapper.CourseMapper;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {

  private final CourseService courseService;
  private final CourseMapper courseMapper;

  @Autowired
  public CourseController(CourseService courseService, CourseMapper courseMapper) {
    this.courseService = courseService;
    this.courseMapper = courseMapper;
  }

//  @GetMapping
//  @ResponseStatus(code = HttpStatus.CREATED)
//  public List<CourseDTO> list() {
//    return courseService.list();
//
//  }

  @GetMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public CoursePageDTO list(@RequestParam int pageNumber, int sizePage) {
    return courseService.list(pageNumber, sizePage);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CourseDTO> findById(@PathVariable @NotNull @Positive Long id) {
    return ResponseEntity
        .ok()
        .body(courseService.findById(id));

  }

  @PostMapping
//  @ResponseStatus(code = HttpStatus.CREATED)
  public ResponseEntity<CourseDTO> create(@RequestBody @Valid CourseDTO courseDto) {
    CourseDTO newCourse = courseService.create(
        courseMapper.toEntity(courseDto)
    );
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(newCourse);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CourseDTO> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid CourseDTO courseDTO) {
    return ResponseEntity.ok().body(
        courseService.update(id, courseMapper.toEntity(courseDTO))
    );


  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable @NotNull @Positive Long id) {
      courseService.delete(id);
  }
}

