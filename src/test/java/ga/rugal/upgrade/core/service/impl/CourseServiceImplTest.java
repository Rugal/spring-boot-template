package ga.rugal.upgrade.core.service.impl;

import ga.rugal.UnitTestBase;
import ga.rugal.upgrade.core.dao.CourseDao;
import ga.rugal.upgrade.core.entity.Course;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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

  @Before
  public void before() {
    this.courseService.setDao(this.courseDao);
  }

  @Test
  public void test() {
    Assert.assertNotNull(this.course);
  }
}
