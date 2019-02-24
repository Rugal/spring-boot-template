package ga.rugal.demo.springmvc.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.http.MediaType;

/**
 *
 * @author Rugal Bernstein
 */
public class CourseControllerIntegrationTest extends ControllerIntegrationTestBase {

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
}
