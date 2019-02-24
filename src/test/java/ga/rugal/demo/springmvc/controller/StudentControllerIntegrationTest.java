package ga.rugal.demo.springmvc.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ga.rugal.demo.core.entity.Student;
import ga.rugal.demo.core.service.StudentService;
import ga.rugal.demo.springmvc.mapper.StudentMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

/**
 *
 * @author Rugal Bernstein
 */
public class StudentControllerIntegrationTest extends ControllerIntegrationTestBase {

  @Autowired
  private StudentService studentService;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private Student student;

  @SneakyThrows
  @Test
  public void createStudent_201() {
    this.mockMvc.perform(post("/student")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(this.objectMapper.writeValueAsString(StudentMapper.INSTANCE.from2(this.student)))
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isCreated())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
  }

  @SneakyThrows
  @Test
  public void deleteStudent_204() {
    final Student save = this.studentService.getDao().save(this.student);
    Assert.assertTrue(this.studentService.getDao().existsById(save.getSid()));

    this.mockMvc.perform(delete("/student/" + save.getSid())
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isNoContent());
  }

  @SneakyThrows
  @Test
  public void deleteStudent_404() {
    this.mockMvc.perform(delete("/student/0")
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isNotFound());
  }

  @SneakyThrows
  @Test
  public void getStudent_200() {
    this.mockMvc.perform(get("/student/1")
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
  }

  @SneakyThrows
  @Test
  public void getStudent_404() {
    this.mockMvc.perform(get("/student/0")
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isNotFound());
  }

  @SneakyThrows
  @Test
  public void updateStudent_200() {
    final Student save = this.studentService.getDao().save(this.student);
    Assert.assertTrue(this.studentService.getDao().existsById(save.getSid()));

    this.mockMvc.perform(put("/student/" + save.getSid())
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(this.objectMapper.writeValueAsString(StudentMapper.INSTANCE.from(this.student)))
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isOk());
  }

  @SneakyThrows
  @Test
  public void updateStudent_404() {
    this.mockMvc.perform(put("/student/0")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(this.objectMapper.writeValueAsString(StudentMapper.INSTANCE.from(this.student)))
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isNotFound());
  }
}
