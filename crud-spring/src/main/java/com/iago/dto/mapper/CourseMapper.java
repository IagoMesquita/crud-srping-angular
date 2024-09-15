package com.iago.dto.mapper;

import com.iago.dto.CourseDTO;
import com.iago.dto.LessonDTO;
import com.iago.enums.Category;
import com.iago.model.Course;
import com.iago.model.Lesson;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

  public CourseDTO toDTO(Course course) {
    if (course == null) { // evitar o nullpointExcepiton
      return null;
    }

    List<LessonDTO> lessonDTO = course.getLessons()
        .stream()
        .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl()))
        .toList();

    return new CourseDTO(
        course.getId(),
        course.getName(),
        course.getCategory().getValue(),
        lessonDTO
    );
  }

  public @Valid Course toEntity(CourseDTO courseDTO) {
    if(courseDTO == null) {
      return null;
    }

    Course course = new Course();

    if(courseDTO.id() != null) {
      course.setId(courseDTO.id());
    }
    course.setName(courseDTO.name());
    course.setCategory(convertCategoryValue(courseDTO.category()));

    List<Lesson> lessonsDb = courseDTO.lessons().stream().map(lessonDTO -> {
      var lesson = new Lesson();
      lesson.setId(lessonDTO.id());
      lesson.setName(lessonDTO.name());
      lesson.setYoutubeUrl(lessonDTO.youtubeURL());
      lesson.setCourse(course); //depara com curso

      return lesson;
    }).toList();
    course.setLessons(lessonsDb);
    return course;
  }

  public Category convertCategoryValue(String value) {
    if (value == null) {
      return null;
    }

    return switch (value) {
      case "Back-end" -> Category.BACK_END;
      case "Front-end" -> Category.FRONT_END;
      default -> throw new IllegalArgumentException("Invalid value " + value);
    };

  }

}
