package com.iago.dto.mapper;

import com.iago.dto.CourseDTO;
import com.iago.enums.Category;
import com.iago.enums.Status;
import com.iago.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

  public CourseDTO toDTO(Course course) {
    if (course == null) { // evitar o nullpointExcepiton
      return null;
    }


    return new CourseDTO(
        course.getId(),
        course.getName(),
        course.getCategory().getValue(),
        course.getLessons()
    );
  }

  public Course toEntity(CourseDTO courseDTO) {
    if(courseDTO == null) {
      return null;
    }

    Course course = new Course();

    if(courseDTO.id() != null) {
      course.setId(courseDTO.id());
    }
    course.setName(courseDTO.name());
    course.setCategory(Category.valueOf(courseDTO.category()));
//    course.setCategory(convertCategoryValue(courseDTO.category()));
    return course;
  }

//  public Category convertCategoryValue(String value) {
//    if (value == null) {
//      return null;
//    }
//
//    return switch (value) {
//      case "Back-end" -> Category.BACK_END;
//      case "Front-end" -> Category.FRONT_END;
//      default -> throw new IllegalArgumentException("Invalid value " + value);
//    };
//
//  }

}
