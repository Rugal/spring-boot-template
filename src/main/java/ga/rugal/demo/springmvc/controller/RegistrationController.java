package ga.rugal.demo.springmvc.controller;

import java.util.Optional;

import ga.rugal.demo.core.entity.Course;
import ga.rugal.demo.core.entity.Registration;
import ga.rugal.demo.core.entity.Student;
import ga.rugal.demo.core.service.CourseService;
import ga.rugal.demo.core.service.RegistrationService;
import ga.rugal.demo.core.service.StudentService;
import ga.rugal.demo.openapi.api.RegistrationApi;
import ga.rugal.demo.openapi.model.NewRegistrationDto;
import ga.rugal.demo.openapi.model.RegistrationDto;
import ga.rugal.demo.springmvc.mapper.RegistrationMapper;

import io.swagger.annotations.Api;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Registration controller.
 *
 * @author Rugal Bernstein
 */
@Api(tags = "RegistrationController")
@RestController
@Slf4j
public class RegistrationController implements RegistrationApi {

  @Autowired
  @Setter
  private RegistrationService registrationService;

  @Autowired
  @Setter
  private CourseService courseService;

  @Autowired
  @Setter
  private StudentService studentService;

  @Override
  public ResponseEntity<RegistrationDto> createRegistration(final @RequestBody
                                                            NewRegistrationDto input) {
    final Optional<Course> optionalCourse = this.courseService.getDao()
      .findById(input.getCid());
    if (optionalCourse.isEmpty()) {
      LOG.error("Course [{}] not found", input.getCid());
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    final Optional<Student> optionalStudent = this.studentService.getDao()
      .findById(input.getSid());
    if (optionalStudent.isEmpty()) {
      LOG.error("Student [{}] not found", input.getSid());
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    final Registration registration = Registration.builder()
      .course(optionalCourse.get())
      .student(optionalStudent.get())
      .grade(input.getGrade())
      .build();

    LOG.info("Create registration");
    return new ResponseEntity<>(RegistrationMapper.INSTANCE.from(this.registrationService.getDao()
                                  .save(registration)),
                                HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Void> deleteRegistration(final @PathVariable("rid") Integer rid) {
    LOG.info("Delete registration [{}]", rid);
    final Optional<Registration> findById = this.registrationService.getDao().findById(rid);
    if (findById.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    this.registrationService.getDao().delete(findById.get());
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @Override
  public ResponseEntity<RegistrationDto> getRegistration(final @PathVariable("rid") Integer rid) {
    LOG.info("Get registration [{}]", rid);
    return this.registrationService.getDao().existsById(rid)
           ? new ResponseEntity<>(RegistrationMapper.INSTANCE.from(this.registrationService.getDao()
                                    .findById(rid).get()),
                                  HttpStatus.OK)
           : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  public ResponseEntity<RegistrationDto> updateRegistration(final @PathVariable("rid") Integer rid,
                                                            final @RequestBody
                                                            RegistrationDto input) {
    if (!this.registrationService.getDao().existsById(rid)) {
      LOG.error("Registration [{}] not found", input.getSid());
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    final Optional<Course> optionalCourse = this.courseService.getDao()
      .findById(input.getCid());
    if (optionalCourse.isEmpty()) {
      LOG.error("Course [{}] not found", input.getCid());
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    final Optional<Student> optionalStudent = this.studentService.getDao()
      .findById(input.getSid());
    if (optionalStudent.isEmpty()) {
      LOG.error("Student [{}] not found", input.getSid());
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    final Registration registration = Registration.builder()
      .course(optionalCourse.get())
      .student(optionalStudent.get())
      .grade(input.getGrade())
      .rid(rid)
      .build();

    LOG.info("Update registration");
    return new ResponseEntity<>(RegistrationMapper.INSTANCE.from(this.registrationService.getDao()
                                  .save(registration)),
                                HttpStatus.OK);
  }
}
