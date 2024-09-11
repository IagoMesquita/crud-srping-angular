package com.iago;

import com.iago.enums.Category;
import com.iago.enums.Status;
import com.iago.model.Course;
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
	}

	private void extracted(CourseRepository courseRepository) {
		courseRepository.deleteAll();

		for (int i = 1; i < 5; i++) {
			Course course = new Course();

			course.setName("Course " + i );
			course.setCategory(Category.BACK_END);
			course.setStatus(Status.ATIVO);

			courseRepository.save(course);
		}

	}
}
