package ga.rugal.demo.springmvc.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ga.rugal.demo.core.entity.Course;
import ga.rugal.demo.core.service.CourseService;
import ga.rugal.demo.springmvc.mapper.CourseMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

/**
 *
 * @author Rugal Bernstein
 */
public class CourseControllerIntegrationTest extends ControllerIntegrationTestBase {

  @Autowired
  private CourseService courseService;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private Course course;

  @SneakyThrows
  @Test
  public void createCourse_201() {
    this.mockMvc.perform(post("/course")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(this.objectMapper.writeValueAsString(CourseMapper.INSTANCE.from2(this.course)))
            .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
  }

  @SneakyThrows
  @Test
  public void deleteCourse_204() {
    this.course.setCid(null);
    final Course save = this.courseService.getDao().save(this.course);
    Assertions.assertTrue(this.courseService.getDao().existsById(save.getCid()));

    this.mockMvc.perform(delete("/course/" + save.getCid())
            .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());
  }

  @SneakyThrows
  @Test
  public void deleteCourse_404() {
    this.mockMvc.perform(delete("/course/0")
            .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isNotFound());
  }

  @SneakyThrows
  @Test
  public void getCourse_200() {
    this.mockMvc.perform(get("/course/1")
            .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
  }

  @SneakyThrows
  @Test
  public void getCourse_404() {
    this.mockMvc.perform(get("/course/0")
            .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isNotFound());
  }

  @SneakyThrows
  @Test
  public void updateCourse_200() {
    final Course save = this.courseService.getDao().save(this.course);
    Assertions.assertTrue(this.courseService.getDao().existsById(save.getCid()));

    this.mockMvc.perform(put("/course/" + save.getCid())
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(this.objectMapper.writeValueAsString(CourseMapper.INSTANCE.from(this.course)))
            .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());
  }

  @SneakyThrows
  @Test
  public void updateCourse_404() {
    this.mockMvc.perform(put("/course/0")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(this.objectMapper.writeValueAsString(CourseMapper.INSTANCE.from(this.course)))
            .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isNotFound());
  }
}
