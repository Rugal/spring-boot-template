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

import ga.rugal.demo.core.dao.StudentDao;
import ga.rugal.demo.core.entity.Student;
import ga.rugal.demo.core.service.StudentService;
import ga.rugal.demo.springmvc.mapper.StudentMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 *
 * @author Rugal Bernstein
 */
@WebMvcTest(StudentController.class)
public class StudentControllerTest extends ControllerUnitTestBase {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private StudentController controller;

  @Autowired
  private StudentService studentService;

  @Autowired
  private StudentDao studentDao;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private Student student;

  @Before
  public void setUp() {
    this.controller.setStudentService(this.studentService);
    given(this.studentService.getDao()).willReturn(this.studentDao);

    given(this.studentDao.existsById(anyInt())).willReturn(true);
    given(this.studentDao.findById(anyInt())).willReturn(Optional.of(this.student));
    given(this.studentDao.save(any())).willReturn(this.student);
  }

  @SneakyThrows
  @Test
  public void createStudent_201() {
    this.mockMvc.perform(post("/student")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(this.objectMapper.writeValueAsString(StudentMapper.INSTANCE.from2(this.student)))
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isCreated())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    verify(this.studentDao, only()).save(any());
  }

  @SneakyThrows
  @Test
  public void deleteStudent_204() {
    this.mockMvc.perform(delete("/student/1")
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isNoContent());
    verify(this.studentDao, times(1)).findById(anyInt());
    verify(this.studentDao, times(1)).delete(any());
  }

  @SneakyThrows
  @Test
  public void deleteStudent_404() {
    given(this.studentDao.findById(anyInt())).willReturn(Optional.empty());

    this.mockMvc.perform(delete("/student/1")
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isNotFound());
    verify(this.studentDao, only()).findById(anyInt());
    verify(this.studentDao, never()).delete(any());
  }

  @SneakyThrows
  @Test
  public void getStudent_200() {
    this.mockMvc.perform(get("/student/1")
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    verify(this.studentDao, times(1)).existsById(anyInt());
    verify(this.studentDao, times(1)).findById(anyInt());
  }

  @SneakyThrows
  @Test
  public void getStudent_404() {
    given(this.studentDao.existsById(anyInt())).willReturn(false);

    this.mockMvc.perform(get("/student/1")
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isNotFound());
    verify(this.studentDao, only()).existsById(anyInt());
    verify(this.studentDao, never()).findById(anyInt());
  }

  @SneakyThrows
  @Test
  public void updateStudent_200() {
    this.mockMvc.perform(put("/student/1")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(this.objectMapper.writeValueAsString(StudentMapper.INSTANCE.from(this.student)))
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isOk());
    verify(this.studentDao, times(1)).existsById(anyInt());
    verify(this.studentDao, times(1)).save(any());
  }

  @SneakyThrows
  @Test
  public void updateStudent_404() {
    given(this.studentDao.existsById(anyInt())).willReturn(false);

    this.mockMvc.perform(put("/student/1")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(this.objectMapper.writeValueAsString(StudentMapper.INSTANCE.from(this.student)))
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isNotFound());
    verify(this.studentDao, only()).existsById(anyInt());
    verify(this.studentDao, never()).save(any());
  }
}
