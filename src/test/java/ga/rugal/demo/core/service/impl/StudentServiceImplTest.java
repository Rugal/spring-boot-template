package ga.rugal.demo.core.service.impl;

import ga.rugal.UnitTestBase;
import ga.rugal.demo.core.dao.StudentDao;
import ga.rugal.demo.core.entity.Student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 *
 * @author Rugal Bernstein
 */
public class StudentServiceImplTest extends UnitTestBase {

  @Autowired
  private Student student;

  private final StudentServiceImpl studentService = new StudentServiceImpl();

  @MockBean
  private StudentDao studentDao;

  @BeforeEach
  public void before() {
    this.studentService.setDao(this.studentDao);
  }

  @Test
  public void test() {
    Assertions.assertNotNull(this.student);
    Assertions.assertNotNull(this.studentService.getDao());
  }
}
