package ga.rugal.demo.core.query;

import ga.rugal.demo.core.dao.CourseDao;
import ga.rugal.demo.core.entity.Course;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * GraphQL mutation.
 *
 * @author Rugal Bernstein
 */
@Component
public class Mutation implements GraphQLMutationResolver {

  @Autowired
  private CourseDao courseDao;

  public Course createCourse(final String name) {
    final Course course = new Course();
    course.setName(name);
    return this.courseDao.save(course);
  }
}
