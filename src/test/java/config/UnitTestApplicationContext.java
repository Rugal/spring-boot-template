package config;

import ga.rugal.demo.core.dao.CourseDao;
import ga.rugal.demo.core.dao.RegistrationDao;
import ga.rugal.demo.core.dao.StudentDao;
import ga.rugal.demo.core.service.CourseService;
import ga.rugal.demo.core.service.RegistrationService;
import ga.rugal.demo.core.service.StudentService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author Rugal Bernstein
 */
@Configuration
public class UnitTestApplicationContext {

  @Bean
  @ConditionalOnMissingBean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  @Scope("prototype")
  public CourseDao courseDao() {
    return Mockito.mock(CourseDao.class);
  }

  @Bean
  @Scope("prototype")
  public StudentDao studentDao() {
    return Mockito.mock(StudentDao.class);
  }

  @Bean
  @Scope("prototype")
  public RegistrationDao registrationDao() {
    return Mockito.mock(RegistrationDao.class);
  }

  @Bean
  @Scope("prototype")
  public CourseService courseService() {
    return Mockito.mock(CourseService.class);
  }

  @Bean
  @Scope("prototype")
  public RegistrationService registrationService() {
    return Mockito.mock(RegistrationService.class);
  }

  @Bean
  @Scope("prototype")
  public StudentService studentService() {
    return Mockito.mock(StudentService.class);
  }
}
