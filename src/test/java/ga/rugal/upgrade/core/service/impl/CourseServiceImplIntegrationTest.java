package ga.rugal.upgrade.core.service.impl;

import ga.rugal.IntegrationTestBase;
import ga.rugal.upgrade.core.service.CourseService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Rugal Bernstein
 */
public class CourseServiceImplIntegrationTest extends IntegrationTestBase {

  @Autowired
  private CourseService courseService;

  @Before
  public void before() {
  }

  @Test
  public void test() {
    Assert.assertNotNull(this.courseService.getDao());
  }
}
