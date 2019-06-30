package ga.rugal.demo.springmvc.controller;

import java.util.Optional;

import ga.rugal.demo.core.entity.Student;
import ga.rugal.demo.core.service.StudentService;
import ga.rugal.demo.openapi.api.StudentApi;
import ga.rugal.demo.openapi.model.NewStudentDto;
import ga.rugal.demo.openapi.model.StudentDto;
import ga.rugal.demo.springmvc.mapper.StudentMapper;

import io.swagger.annotations.Api;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Student controller.
 *
 * @author Rugal Bernstein
 */
@Api(tags = "StudentController")
@RestController
@Slf4j
public class StudentController implements StudentApi {

  @Autowired
  @Setter
  private StudentService studentService;

  @Override
  public ResponseEntity<StudentDto> createStudent(final NewStudentDto input) {
    LOG.info("Create student");
    final Student save = this.studentService.getDao().save(StudentMapper.INSTANCE.to(input));
    return new ResponseEntity<>(StudentMapper.INSTANCE.from(save), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Void> deleteStudent(final Integer sid) {
    LOG.info("Delete student [{}]", sid);
    final Optional<Student> findById = this.studentService.getDao().findById(sid);
    if (findById.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    this.studentService.getDao().delete(findById.get());
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @Override
  public ResponseEntity<StudentDto> getStudent(final Integer sid) {
    LOG.info("Get student [{}]", sid);
    return this.studentService.getDao().existsById(sid)
           ? new ResponseEntity<>(StudentMapper.INSTANCE.from(this.studentService.getDao()
                                    .findById(sid).get()),
                                  HttpStatus.OK)
           : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  public ResponseEntity<StudentDto> updateStudent(final Integer sid, final StudentDto input) {

    if (!this.studentService.getDao().existsById(sid)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    final Student student = StudentMapper.INSTANCE.to(input);
    student.setSid(sid);
    LOG.info("Update student [{}]", sid);
    return new ResponseEntity<>(StudentMapper.INSTANCE.from(this.studentService.getDao()
                                  .save(student)),
                                HttpStatus.OK);
  }
}
