package ga.rugal.demo.core.service.impl;

import ga.rugal.IntegrationTestBase;
import ga.rugal.demo.core.service.StudentService;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Rugal Bernstein
 */
public class StudentServiceImplIntegrationTest extends IntegrationTestBase {

  @Autowired
  private StudentService studentService;

  @Test
  public void test() {
    Assert.assertNotNull(this.studentService.getDao());
  }
}
