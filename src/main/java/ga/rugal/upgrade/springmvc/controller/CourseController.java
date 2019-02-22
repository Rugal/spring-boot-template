package ga.rugal.upgrade.springmvc.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import ga.rugal.upgrade.core.entity.Course;
import ga.rugal.upgrade.core.service.CourseService;
import ga.rugal.upgrade.springmvc.mapper.CourseMapper;
import ga.rugal.upgrade.swagger.api.CourseApi;

import com.fasterxml.jackson.databind.ObjectMapper;
import ga.rugal.upgrade.swagger.model.CourseDto;
import ga.rugal.upgrade.swagger.model.NewCourseDto;
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
public class CourseController implements CourseApi {

  @Autowired
  private CourseService courseService;

  @Override
  public ResponseEntity<CourseDto> updateCourse(final Integer cid, final CourseDto input) {

    if (!this.courseService.getDao().existsById(cid)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    final Course course = CourseMapper.INSTANCE.to(input);
    course.setCid(cid);
    final Course output = this.courseService.getDao().save(course);
    return new ResponseEntity<>(CourseMapper.INSTANCE.from(output), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<CourseDto> getCourse(final Integer cid) {
    return this.courseService.getDao().existsById(cid)
           ? new ResponseEntity<>(CourseMapper.INSTANCE.from(this.courseService.getDao()
                                                              .findById(cid).get()),
                                  HttpStatus.OK)
           : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  public ResponseEntity<Void> deleteCourse(final Integer cid) {
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @Override
  public ResponseEntity<CourseDto> createCourse(final NewCourseDto course) {
    return new ResponseEntity<>(new CourseDto(), HttpStatus.CREATED);
  }

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
}
