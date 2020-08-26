package ga.rugal.demo.springmvc.graphql;

import java.util.Optional;

import ga.rugal.demo.core.entity.Course;
import ga.rugal.demo.core.entity.Registration;
import ga.rugal.demo.core.entity.Student;
import ga.rugal.demo.core.service.CourseService;
import ga.rugal.demo.core.service.RegistrationService;
import ga.rugal.demo.core.service.StudentService;
import ga.rugal.demo.graphql.CourseDto;
import ga.rugal.demo.graphql.CourseInputDto;
import ga.rugal.demo.graphql.MutationResolver;
import ga.rugal.demo.graphql.RegistrationDto;
import ga.rugal.demo.graphql.RegistrationInputDto;
import ga.rugal.demo.graphql.StudentDto;
import ga.rugal.demo.graphql.StudentInputDto;
import ga.rugal.demo.springmvc.mapper.CourseMapper;
import ga.rugal.demo.springmvc.mapper.RegistrationMapper;
import ga.rugal.demo.springmvc.mapper.StudentMapper;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * GraphQL mutation.
 *
 * @author Rugal Bernstein
 */
@Component
public class Mutation implements GraphQLMutationResolver, MutationResolver {

  @Autowired
  private CourseService courseService;

  @Autowired
  private StudentService studentService;

  @Autowired
  private RegistrationService registrationService;

  /**
   * create course with course name.
   *
   * @param input course input object
   *
   * @return saved course object
   *
   * @throws Exception while query database
   */
  @Override
  public CourseDto addCourse(final CourseInputDto input) throws Exception {
    final Course course = CourseMapper.I.to(input);
    return CourseMapper.I.from(this.courseService.getDao().save(course));
  }

  @Override
  public StudentDto addStudent(final StudentInputDto input) throws Exception {
    final Student course = StudentMapper.I.to(input);
    return StudentMapper.I.from(this.studentService.getDao().save(course));
  }

  @Override
  public RegistrationDto addRegistration(final RegistrationInputDto input) throws Exception {
    final Optional<Course> courseOptional = this.courseService.getDao().findById(input.getCid());
    final Optional<Student> studentOptional = this.studentService.getDao().findById(input.getSid());
    if (courseOptional.isEmpty() || studentOptional.isEmpty()) {
      return null;
    }
    final Registration r = new Registration(null,
                                            input.getGrade(),
                                            courseOptional.get(),
                                            studentOptional.get());
    return RegistrationMapper.I.from(this.registrationService.getDao().save(r));
  }
}
