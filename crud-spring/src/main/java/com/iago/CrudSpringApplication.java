package com.iago;

import com.iago.enums.Category;
import com.iago.enums.Status;
import com.iago.model.Course;
import com.iago.model.Lesson;
import com.iago.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudSpringApplication {

  public static void main(String[] args) {
    SpringApplication.run(CrudSpringApplication.class, args);
  }


  @Bean
  CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> extracted(courseRepository);
//    return args -> {
//			courseRepository.deleteAll();
//
//			Course course = new Course();
//			course.setName("JAVA");
//			course.setCategory(Category.BACK_END);
//			course.setStatus(Status.ATIVO);
//
//
//			Lesson lesson1 = new Lesson();
//			lesson1.setName("Aula 1: Fundamentos");
//			lesson1.setYoutubeUrl("1fd654fhd9d");
//			lesson1.setCourse(course);
//			course.getLessons().add(lesson1);
//
//			Lesson lesson2 = new Lesson();
//			lesson2.setName("Aula 2: OOP");
//			lesson2.setYoutubeUrl("1fdsfgj98j");
//			lesson2.setCourse(course);
//			course.getLessons().add(lesson2);
//
//			courseRepository.save(course);
//		};

  }

  private void extracted(CourseRepository courseRepository) {
    courseRepository.deleteAll();

    for (int i = 1; i < 20; i++) {
      Course course = new Course();

      course.setName("Course " + i);
      course.setCategory(Category.BACK_END);
      course.setStatus(Status.ATIVO);

      Lesson lesson = new Lesson();
      lesson.setName("javinha avancado" + i);
      lesson.setYoutubeUrl("1fd654f5h7");
			lesson.setCourse(course);
      course.getLessons().add(lesson);

      courseRepository.save(course);
    }

    Lesson lesson = new Lesson();
    lesson.setName("javinha avancado");
    lesson.setYoutubeUrl("1fd654f5h7");


  }
}
