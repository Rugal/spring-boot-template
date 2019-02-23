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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import ga.rugal.demo.core.dao.CourseDao;
import ga.rugal.demo.core.entity.Course;
import ga.rugal.demo.core.service.CourseService;
import ga.rugal.demo.swagger.model.CourseDto;
import ga.rugal.demo.swagger.model.NewCourseDto;

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
@WebMvcTest(CourseController.class)
public class CourseControllerTest extends UnitTestControllerBase {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private CourseController controller;

  @Autowired
  private CourseService courseService;

  @Autowired
  private CourseDao courseDao;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private Course course;

  @Autowired
  private NewCourseDto newCourseDto;

  @Autowired
  private CourseDto courseDto;

  @Before
  public void setUp() {
    this.controller.setCourseService(this.courseService);
    given(this.courseService.getDao()).willReturn(this.courseDao);

    given(this.courseDao.existsById(anyInt())).willReturn(true);
    given(this.courseDao.findById(anyInt())).willReturn(Optional.of(this.course));
    given(this.courseDao.save(any())).willReturn(this.course);
  }

  @SneakyThrows
  @Test
  public void getCourse_200() {
    this.mockMvc.perform(get("/course/1")
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    verify(this.courseDao, times(1)).existsById(anyInt());
    verify(this.courseDao, times(1)).findById(anyInt());
  }

  @SneakyThrows
  @Test
  public void getCourse_404() {
    given(this.courseDao.existsById(anyInt())).willReturn(false);

    this.mockMvc.perform(get("/course/1")
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isNotFound());
    verify(this.courseDao, only()).existsById(anyInt());
    verify(this.courseDao, never()).findById(anyInt());
  }

  @SneakyThrows
  @Test
  public void deleteCourse_204() {
    this.mockMvc.perform(delete("/course/1")
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isNoContent());
    verify(this.courseDao, times(1)).findById(anyInt());
    verify(this.courseDao, times(1)).delete(any());
  }

  @SneakyThrows
  @Test
  public void deleteCourse_404() {
    given(this.courseDao.findById(anyInt())).willReturn(Optional.empty());

    this.mockMvc.perform(delete("/course/1")
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isNotFound());
    verify(this.courseDao, only()).findById(anyInt());
    verify(this.courseDao, never()).delete(any());
  }

  @SneakyThrows
  @Test
  public void createCourse_201() {
    this.mockMvc.perform(post("/course")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(this.objectMapper.writeValueAsString(this.newCourseDto))
      .accept(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isCreated())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    verify(this.courseDao, only()).save(any());
  }
}
