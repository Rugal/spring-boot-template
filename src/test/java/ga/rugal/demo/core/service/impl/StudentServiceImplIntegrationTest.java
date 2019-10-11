package ga.rugal.demo.core.service.impl;

import ga.rugal.IntegrationTestBase;
import ga.rugal.demo.core.service.StudentService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
    Assertions.assertNotNull(this.studentService.getDao());
  }
}
