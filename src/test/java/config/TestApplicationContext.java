package config;

import ga.rugal.demo.core.entity.Course;
import ga.rugal.demo.core.entity.Registration;
import ga.rugal.demo.core.entity.Student;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author Rugal Bernstein
 */
@Configuration
public class TestApplicationContext {

  @Bean
  public Faker faker() {
    return new Faker();
  }

  @Bean
  @Scope("prototype")
  public Student student(final Faker faker) {
    final Student student = new Student();
    student.setName(faker.name().firstName());
    student.setSid(faker.number().numberBetween(1, 100));
    return student;
  }

  @Bean
  @Scope("prototype")
  public Course course(final Faker faker) {
    final Course course = new Course();
    course.setCid(faker.number().numberBetween(1, 100));
    course.setName(faker.name().firstName());
    return course;
  }

  @Bean
  @Scope("prototype")
  public Registration registration(final Student student, final Course course, final Faker faker) {
    return Registration.builder()
      .course(course)
      .student(student)
      .rid(faker.number().numberBetween(1, 100))
      .grade(faker.number().numberBetween(1, 100))
      .build();
  }
}
