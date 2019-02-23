package ga.rugal.demo.springmvc.controller;

import java.util.Optional;

import ga.rugal.demo.core.entity.Course;
import ga.rugal.demo.core.service.CourseService;
import ga.rugal.demo.springmvc.mapper.CourseMapper;
import ga.rugal.demo.swagger.api.CourseApi;
import ga.rugal.demo.swagger.model.CourseDto;
import ga.rugal.demo.swagger.model.NewCourseDto;

import io.swagger.annotations.Api;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Course controller.
 *
 * @author Rugal Bernstein
 */
@Api(tags = "CourseController")
@RestController
@Slf4j
public class CourseController implements CourseApi {

  @Autowired
  @Setter
  private CourseService courseService;

  @Override
  public ResponseEntity<CourseDto> updateCourse(final Integer cid, final CourseDto input) {

    if (!this.courseService.getDao().existsById(cid)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    final Course course = CourseMapper.INSTANCE.to(input);
    course.setCid(cid);
    LOG.info("Update course [{}]", cid);
    final Course output = this.courseService.getDao().save(course);
    return new ResponseEntity<>(CourseMapper.INSTANCE.from(output), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<CourseDto> getCourse(final Integer cid) {
    LOG.info("Get course [{}]", cid);
    return this.courseService.getDao().existsById(cid)
           ? new ResponseEntity<>(CourseMapper.INSTANCE.from(this.courseService.getDao()
                                    .findById(cid).get()),
                                  HttpStatus.OK)
           : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  public ResponseEntity<Void> deleteCourse(final Integer cid) {
    LOG.info("Delete course [{}]", cid);
    final Optional<Course> findById = this.courseService.getDao().findById(cid);
    if (findById.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    this.courseService.getDao().delete(findById.get());
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @Override
  public ResponseEntity<CourseDto> createCourse(final NewCourseDto course) {
    LOG.info("Create course");
    final Course save = this.courseService.getDao().save(CourseMapper.INSTANCE.to(course));
    return new ResponseEntity<>(CourseMapper.INSTANCE.from(save), HttpStatus.CREATED);
  }
}
