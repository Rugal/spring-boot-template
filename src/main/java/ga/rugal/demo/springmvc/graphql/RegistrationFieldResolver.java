package ga.rugal.demo.springmvc.graphql;

import ga.rugal.demo.core.entity.Registration;
import ga.rugal.demo.core.service.RegistrationService;
import ga.rugal.demo.graphql.CourseDto;
import ga.rugal.demo.graphql.RegistrationDto;
import ga.rugal.demo.graphql.RegistrationResolver;
import ga.rugal.demo.graphql.StudentDto;
import ga.rugal.demo.springmvc.mapper.CourseMapper;
import ga.rugal.demo.springmvc.mapper.StudentMapper;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Student query resolver.
 *
 * @author Rugal Bernstein
 */
@Component
public class RegistrationFieldResolver implements GraphQLResolver<RegistrationDto>,
                                                  RegistrationResolver {

  @Autowired
  private RegistrationService registrationService;

  @Override
  public StudentDto student(final RegistrationDto registrationDto) throws Exception {
    final Registration r = this.registrationService.getDao()
      .findById(registrationDto.getRid()).get();
    return StudentMapper.I.from(r.getStudent());
  }

  @Override
  public CourseDto course(final RegistrationDto registrationDto) throws Exception {
    final Registration r = this.registrationService.getDao()
      .findById(registrationDto.getRid()).get();
    return CourseMapper.I.from(r.getCourse());
  }
}
