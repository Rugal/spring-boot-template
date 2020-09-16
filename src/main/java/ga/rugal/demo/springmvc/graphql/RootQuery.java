package ga.rugal.demo.springmvc.graphql;

import ga.rugal.demo.core.service.CourseService;
import ga.rugal.demo.core.service.RegistrationService;
import ga.rugal.demo.core.service.StudentService;
import ga.rugal.demo.graphql.CourseDto;
import ga.rugal.demo.graphql.QueryResolver;
import ga.rugal.demo.graphql.RegistrationDto;
import ga.rugal.demo.graphql.StudentDto;
import ga.rugal.demo.springmvc.mapper.CourseMapper;
import ga.rugal.demo.springmvc.mapper.RegistrationMapper;
import ga.rugal.demo.springmvc.mapper.StudentMapper;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * GraphQL root query for Course.
 *
 * @author Rugal Bernstein
 */
@Component
public class RootQuery implements GraphQLQueryResolver, QueryResolver {

  @Autowired
  private CourseService courseService;

  @Autowired
  private StudentService studentService;

  @Autowired
  private RegistrationService registrationService;

  @Override
  public StudentDto student(final Integer sid) throws Exception {
    return StudentMapper.I.from(this.studentService.getDao().findById(sid).orElse(null));
  }

  @Override
  public CourseDto course(final Integer cid) throws Exception {
    return CourseMapper.I.from(this.courseService.getDao().findById(cid).orElse(null));
  }

  @Override
  public RegistrationDto registration(final Integer rid) throws Exception {
    return RegistrationMapper.I.from(this.registrationService.getDao().findById(rid).orElse(null));
  }
}
