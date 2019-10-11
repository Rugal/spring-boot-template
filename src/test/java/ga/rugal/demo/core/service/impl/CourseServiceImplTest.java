package ga.rugal.demo.core.service.impl;

import ga.rugal.UnitTestBase;
import ga.rugal.demo.core.dao.CourseDao;
import ga.rugal.demo.core.entity.Course;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 *
 * @author Rugal Bernstein
 */
public class CourseServiceImplTest extends UnitTestBase {

  @Autowired
  private Course course;

  private final CourseServiceImpl courseService = new CourseServiceImpl();

  @MockBean
  private CourseDao courseDao;

  @BeforeEach
  public void before() {
    this.courseService.setDao(this.courseDao);
  }

  @Test
  public void test() {
    Assertions.assertNotNull(this.course);
    Assertions.assertNotNull(this.courseService.getDao());
  }
}
