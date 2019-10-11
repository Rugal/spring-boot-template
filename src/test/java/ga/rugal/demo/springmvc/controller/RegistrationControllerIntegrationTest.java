package ga.rugal.demo.springmvc.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ga.rugal.demo.core.entity.Registration;
import ga.rugal.demo.core.service.CourseService;
import ga.rugal.demo.core.service.RegistrationService;
import ga.rugal.demo.core.service.StudentService;
import ga.rugal.demo.springmvc.mapper.RegistrationMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

/**
 *
 * @author Rugal Bernstein
 */
public class RegistrationControllerIntegrationTest extends ControllerIntegrationTestBase {

  @Autowired
  private RegistrationService registrationService;

  @Autowired
  private StudentService studentService;

  @Autowired
  private CourseService courseService;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private Registration registration;

  @BeforeEach
  public void before() {
    this.registration.setCourse(this.courseService.getDao().save(this.registration.getCourse()));
    this.registration.setStudent(this.studentService.getDao().save(this.registration.getStudent()));
    this.registration = this.registrationService.getDao().save(this.registration);
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
  }

  @SneakyThrows
  @Test
  public void createRegistration_no_course_404() {
    this.registration.getCourse().setCid(0);

    this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(this.objectMapper.writeValueAsString(RegistrationMapper.INSTANCE.from2(this.registration)))
            .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isNotFound());
  }

  @SneakyThrows
  @Test
  public void createRegistration_no_student_404() {
    this.registration.getStudent().setSid(0);

    this.mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(this.objectMapper.writeValueAsString(RegistrationMapper.INSTANCE.from2(this.registration)))
            .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isNotFound());
  }

  @SneakyThrows
  @Test
  public void deleteRegistration_204() {
    this.mockMvc.perform(delete("/registration/" + this.registration.getRid())
            .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());
  }

  @SneakyThrows
  @Test
  public void deleteRegistration_404() {
    this.mockMvc.perform(delete("/registration/0")
            .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isNotFound());
  }

  @SneakyThrows
  @Test
  public void getRegistration_200() {
    this.mockMvc.perform(get("/registration/" + this.registration.getRid())
            .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
  }

  @SneakyThrows
  @Test
  public void getRegistration_404() {
    this.mockMvc.perform(get("/registration/0")
            .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isNotFound());
  }

  @SneakyThrows
  @Test
  public void updateRegistration_200() {
    this.mockMvc.perform(put("/registration/" + this.registration.getRid())
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(this.objectMapper.writeValueAsString(RegistrationMapper.INSTANCE.from(this.registration)))
            .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
  }

  @SneakyThrows
  @Test
  public void updateRegistration_no_registration_404() {
    this.mockMvc.perform(put("/registration/0")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(this.objectMapper.writeValueAsString(RegistrationMapper.INSTANCE.from(this.registration)))
            .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isNotFound());
  }

  @SneakyThrows
  @Test
  public void updateRegistration_no_course_404() {
    this.registration.getCourse().setCid(0);

    this.mockMvc.perform(put("/registration/" + this.registration.getRid())
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(this.objectMapper.writeValueAsString(RegistrationMapper.INSTANCE.from(this.registration)))
            .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isNotFound());
  }

  @SneakyThrows
  @Test
  public void updateRegistration_no_student_404() {
    this.registration.getStudent().setSid(0);

    this.mockMvc.perform(put("/registration/" + this.registration.getRid())
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(this.objectMapper.writeValueAsString(RegistrationMapper.INSTANCE.from(this.registration)))
            .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isNotFound());
  }
}
