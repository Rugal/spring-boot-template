package ga.rugal.demo.springmvc.graphql;

import java.util.Optional;

import ga.rugal.demo.core.dao.CourseDao;
import ga.rugal.demo.core.dao.RegistrationDao;
import ga.rugal.demo.core.dao.StudentDao;
import ga.rugal.demo.core.entity.Course;
import ga.rugal.demo.core.entity.Registration;
import ga.rugal.demo.core.entity.Student;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * GraphQL root query for Course.
 *
 * @author Rugal Bernstein
 */
@Component
public class RootQuery implements GraphQLQueryResolver {

  @Autowired
  private CourseDao courseDao;

  @Autowired
  private StudentDao studentDao;

  @Autowired
  private RegistrationDao registrationDao;

  public Optional<Student> getStudent(final int id) {
    return this.studentDao.findById(id);
  }

  public Optional<Course> getCourse(final int id) {
    return this.courseDao.findById(id);
  }

  public Optional<Registration> getRegistration(final int id) {
    return this.registrationDao.findById(id);
  }
}
