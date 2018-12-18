package config;

import ga.rugal.upgrade.core.dao.CourseDao;
import ga.rugal.upgrade.core.dao.RegistrationDao;
import ga.rugal.upgrade.core.dao.StudentDao;
import ga.rugal.upgrade.core.service.CourseService;
import ga.rugal.upgrade.core.service.RegistrationService;
import ga.rugal.upgrade.core.service.StudentService;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Rugal Bernstein
 */
@Configuration
public class UnitTestApplicationContext {

  @Bean
  public CourseDao courseDao() {
    return Mockito.mock(CourseDao.class);
  }

  @Bean
  public StudentDao studentDao() {
    return Mockito.mock(StudentDao.class);
  }

  @Bean
  public RegistrationDao registrationDao() {
    return Mockito.mock(RegistrationDao.class);
  }

  @Bean
  public CourseService courseService() {
    return Mockito.mock(CourseService.class);
  }

  @Bean
  public RegistrationService registrationService() {
    return Mockito.mock(RegistrationService.class);
  }

  @Bean
  public StudentService studentService() {
    return Mockito.mock(StudentService.class);
  }
}
