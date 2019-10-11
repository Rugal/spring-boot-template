package ga.rugal.demo.springmvc.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import ga.rugal.demo.core.dao.CourseDao;
import ga.rugal.demo.core.dao.RegistrationDao;
import ga.rugal.demo.core.dao.StudentDao;
import ga.rugal.demo.core.entity.Course;
import ga.rugal.demo.core.entity.Registration;
import ga.rugal.demo.core.entity.Student;
import ga.rugal.demo.core.service.CourseService;
import ga.rugal.demo.core.service.RegistrationService;
import ga.rugal.demo.core.service.StudentService;
import ga.rugal.demo.springmvc.mapper.RegistrationMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 *
 * @author Rugal Bernstein
 */
@WebMvcTest(RegistrationController.class)
public class RegistrationControllerTest extends ControllerUnitTestBase {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private RegistrationController controller;

  @Autowired
  private RegistrationService registrationService;

  @Autowired
  private RegistrationDao registrationDao;

  @Autowired
  private StudentService studentService;

  @Autowired
  private StudentDao studentDao;

  @Autowired
  private CourseService courseService;

  @Autowired
  private CourseDao courseDao;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private Course course;

  @Autowired
  private Student student;

  @Autowired
  private Registration registration;

  @BeforeEach
  public void setUp() {
    this.controller.setRegistrationService(this.registrationService);
    this.controller.setStudentService(this.studentService);
    this.controller.setCourseService(this.courseService);

    given(this.registrationService.getDao()).willReturn(this.registrationDao);
    given(this.studentService.getDao()).willReturn(this.studentDao);
    given(this.courseService.getDao()).willReturn(this.courseDao);

    given(this.registrationDao.findById(anyInt())).willReturn(Optional.of(this.registration));
    given(this.registrationDao.existsById(anyInt())).willReturn(true);
    given(this.registrationDao.save(any())).willReturn(this.registration);

    given(this.courseDao.findById(anyInt())).willReturn(Optional.of(this.course));

    given(this.studentDao.findById(anyInt())).willReturn(Optional.of(this.student));
  }

  @SneakyThrows
  @Test
  public void createRegistration_201() {
    this.mockMvc.perform(post("/registration")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(this.objectMapper.writeValueAsString(RegistrationMapper.INSTANCE.from2(this.registration)))
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isCreated())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

    verify(this.courseDao, only()).findById(any());
    verify(this.studentDao, only()).findById(any());
    verify(this.registrationDao, only()).save(any());
  }

  @SneakyThrows
  @Test
  public void createRegistration_no_course_404() {
    given(this.courseDao.findById(anyInt())).willReturn(Optional.empty());

    this.mockMvc.perform(post("/registration")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(this.objectMapper.writeValueAsString(RegistrationMapper.INSTANCE.from2(this.registration)))
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isNotFound());

    verify(this.courseDao, only()).findById(any());
    verify(this.studentDao, never()).findById(any());
    verify(this.registrationDao, never()).save(any());
  }

  @SneakyThrows
  @Test
  public void createRegistration_no_student_404() {
    given(this.studentDao.findById(anyInt())).willReturn(Optional.empty());

    this.mockMvc.perform(post("/registration")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(this.objectMapper.writeValueAsString(RegistrationMapper.INSTANCE.from2(this.registration)))
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isNotFound());

    verify(this.courseDao, only()).findById(any());
    verify(this.studentDao, only()).findById(any());
    verify(this.registrationDao, never()).save(any());
  }

  @SneakyThrows
  @Test
  public void deleteRegistration_204() {
    this.mockMvc.perform(delete("/registration/1")
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isNoContent());
    verify(this.registrationDao, times(1)).findById(anyInt());
    verify(this.registrationDao, times(1)).delete(any());
  }

  @SneakyThrows
  @Test
  public void deleteRegistration_404() {
    given(this.registrationDao.findById(anyInt())).willReturn(Optional.empty());

    this.mockMvc.perform(delete("/registration/1")
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isNotFound());
    verify(this.registrationDao, only()).findById(anyInt());
    verify(this.registrationDao, never()).delete(any());
  }

  @SneakyThrows
  @Test
  public void getRegistration_200() {
    this.mockMvc.perform(get("/registration/1")
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    verify(this.registrationDao, times(1)).existsById(anyInt());
    verify(this.registrationDao, times(1)).findById(anyInt());
  }

  @SneakyThrows
  @Test
  public void getRegistration_404() {
    given(this.registrationDao.existsById(anyInt())).willReturn(false);

    this.mockMvc.perform(get("/registration/1")
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isNotFound());
    verify(this.registrationDao, only()).existsById(anyInt());
    verify(this.registrationDao, never()).findById(anyInt());
  }

  @SneakyThrows
  @Test
  public void updateRegistration_200() {
    this.mockMvc.perform(put("/registration/1")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(this.objectMapper.writeValueAsString(RegistrationMapper.INSTANCE.from(this.registration)))
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    verify(this.registrationDao, times(1)).existsById(anyInt());
    verify(this.courseDao, times(1)).findById(any());
    verify(this.studentDao, times(1)).findById(any());
    verify(this.registrationDao, times(1)).save(any());
  }

  @SneakyThrows
  @Test
  public void updateRegistration_no_registration_404() {
    given(this.registrationDao.existsById(anyInt())).willReturn(false);

    this.mockMvc.perform(put("/registration/1")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(this.objectMapper.writeValueAsString(RegistrationMapper.INSTANCE.from(this.registration)))
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isNotFound());

    verify(this.registrationDao, only()).existsById(anyInt());
    verify(this.courseDao, never()).findById(any());
    verify(this.studentDao, never()).findById(any());
    verify(this.registrationDao, never()).save(any());
  }

  @SneakyThrows
  @Test
  public void updateRegistration_no_course_404() {
    given(this.courseDao.findById(anyInt())).willReturn(Optional.empty());

    this.mockMvc.perform(put("/registration/1")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(this.objectMapper.writeValueAsString(RegistrationMapper.INSTANCE.from(this.registration)))
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isNotFound());

    verify(this.registrationDao, only()).existsById(anyInt());
    verify(this.courseDao, only()).findById(any());
    verify(this.studentDao, never()).findById(any());
    verify(this.registrationDao, never()).save(any());
  }

  @SneakyThrows
  @Test
  public void updateRegistration_no_student_404() {
    given(this.studentDao.findById(anyInt())).willReturn(Optional.empty());

    this.mockMvc.perform(put("/registration/1")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(this.objectMapper.writeValueAsString(RegistrationMapper.INSTANCE.from(this.registration)))
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isNotFound());

    verify(this.registrationDao, only()).existsById(anyInt());
    verify(this.courseDao, only()).findById(any());
    verify(this.studentDao, only()).findById(any());
    verify(this.registrationDao, never()).save(any());
  }
}
