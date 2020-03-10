package ga.rugal.demo.springmvc.graphql;

import ga.rugal.demo.core.entity.Course;
import ga.rugal.demo.core.entity.Registration;
import ga.rugal.demo.core.entity.Student;

import com.coxautodev.graphql.tools.GraphQLResolver;

/**
 * Student query resolver.
 *
 * @author Rugal Bernstein
 */
public class RegistrationResolver implements GraphQLResolver<Registration> {

  public Student getStudent(final Registration r) {
    return r.getStudent();
  }

  public Course getCourse(final Registration r) {
    return r.getCourse();
  }
}
