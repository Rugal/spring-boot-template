package ga.rugal.upgrade.springmvc.controller;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import ga.rugal.upgrade.swagger.api.CourseApi;
import ga.rugal.upgrade.swagger.request.CourseDto;
import ga.rugal.upgrade.swagger.request.NewCourseDto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

/**
 * Course controller.
 *
 * @author Rugal Bernstein
 */
@Controller
public class CourseController implements CourseApi {

  @Override
  public Optional<ObjectMapper> getObjectMapper() {
    return CourseApi.super.getObjectMapper();
  }

  @Override
  public Optional<HttpServletRequest> getRequest() {
    return CourseApi.super.getRequest();
  }

  @Override
  public Optional<String> getAcceptHeader() {
    return CourseApi.super.getAcceptHeader();
  }

  @Override
  public ResponseEntity<CourseDto> updateCourse(final Integer cid, final CourseDto course) {
    return new ResponseEntity<>(new CourseDto(), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<CourseDto> getCourse(final Integer cid) {
    return new ResponseEntity<>(new CourseDto(), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Void> deleteCourse(final Integer cid) {
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @Override
  public ResponseEntity<CourseDto> createCourse(final NewCourseDto course) {
    return new ResponseEntity<>(new CourseDto(), HttpStatus.CREATED);
  }
}
