package ga.rugal.demo.springmvc.graphql;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import ga.rugal.UnitTestBase;
import ga.rugal.demo.core.dao.CourseDao;
import ga.rugal.demo.core.dao.RegistrationDao;
import ga.rugal.demo.core.dao.StudentDao;
import ga.rugal.demo.core.entity.Course;
import ga.rugal.demo.core.entity.Registration;
import ga.rugal.demo.core.entity.Student;
import ga.rugal.demo.core.service.CourseService;
import ga.rugal.demo.core.service.RegistrationService;
import ga.rugal.demo.core.service.StudentService;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@GraphQLTest
public class RootQueryTest extends UnitTestBase {

  @Autowired
  private GraphQLTestTemplate template;

  @Autowired
  private Student student;

  @Autowired
  private Course course;

  @Autowired
  private Registration registration;

  @MockBean
  private StudentDao studentDao;

  @MockBean
  private StudentService studentService;

  @MockBean
  private CourseDao courseDao;

  @MockBean
  private CourseService courseService;

  @MockBean
  private RegistrationDao registrationDao;

  @MockBean
  private RegistrationService registrationService;

  @BeforeEach
  public void setUp() {
    when(this.studentService.getDao()).thenReturn(this.studentDao);

    when(this.courseService.getDao()).thenReturn(this.courseDao);

    when(this.registrationService.getDao()).thenReturn(this.registrationDao);

    when(this.studentDao.findById(any())).thenReturn(Optional.of(this.student));

    when(this.courseDao.findById(any())).thenReturn(Optional.of(this.course));

    when(this.registrationDao.findById(any())).thenReturn(Optional.of(this.registration));
  }

  @SneakyThrows
  @Test
  public void student_null() {
    when(this.studentDao.findById(any())).thenReturn(Optional.empty());

    final GraphQLResponse r = this.template.postForResource("graphql/student.graphql");

    Assertions.assertNotNull(r);
    Assertions.assertTrue(r.isOk());
    Assertions.assertNull(r.get("$.data.student"));
    verify(this.studentDao).findById(any());
  }

  @SneakyThrows
  @Test
  public void student() {
    final GraphQLResponse r = this.template.postForResource("graphql/student.graphql");

    Assertions.assertNotNull(r);
    Assertions.assertTrue(r.isOk());
    Assertions.assertEquals(this.student.getName(), r.get("$.data.student.name"));
    verify(this.studentDao).findById(any());
  }

  @SneakyThrows
  @Test
  public void course_null() {
    when(this.courseDao.findById(any())).thenReturn(Optional.empty());

    final GraphQLResponse r = this.template.postForResource("graphql/course.graphql");

    Assertions.assertNotNull(r);
    Assertions.assertTrue(r.isOk());
    Assertions.assertNull(r.get("$.data.course"));
    verify(this.courseDao).findById(any());
  }

  @SneakyThrows
  @Test
  public void course() {
    final GraphQLResponse r = this.template.postForResource("graphql/course.graphql");

    Assertions.assertNotNull(r);
    Assertions.assertTrue(r.isOk());
    Assertions.assertEquals(this.course.getName(), r.get("$.data.course.name"));
    verify(this.courseDao).findById(any());
  }

  @SneakyThrows
  @Test
  public void registration_null() {
    when(this.registrationDao.findById(any())).thenReturn(Optional.empty());

    final GraphQLResponse r = this.template.postForResource("graphql/registration.graphql");

    Assertions.assertNotNull(r);
    Assertions.assertTrue(r.isOk());
    Assertions.assertNull(r.get("$.data.registration"));
    verify(this.registrationDao).findById(any());
  }

  @SneakyThrows
  @Test
  public void registration() {
    final GraphQLResponse r = this.template.postForResource("graphql/registration.graphql");

    Assertions.assertNotNull(r);
    Assertions.assertTrue(r.isOk());
    final Integer rid = r.context().read("$.data.registration.rid");
    Assertions.assertEquals(this.registration.getRid(), rid);
    verify(this.registrationDao).findById(any());
  }
}
