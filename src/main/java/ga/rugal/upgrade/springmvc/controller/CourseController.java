package ga.rugal.upgrade.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import ga.rugal.upgrade.swagger.api.CourseApiController;
import ga.rugal.upgrade.swagger.request.CourseDto;
import ga.rugal.upgrade.swagger.request.NewCourseDto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

/**
 * Course controller.
 *
 * @author Rugal Bernstein
 */
@Controller
public class CourseController extends CourseApiController {

  @Autowired
  public CourseController(final ObjectMapper jacksonObjectMapper,
                          final HttpServletRequest request) {
    super(jacksonObjectMapper, request);
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
