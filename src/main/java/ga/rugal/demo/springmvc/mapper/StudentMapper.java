package ga.rugal.demo.springmvc.mapper;

import ga.rugal.demo.core.entity.Student;
import ga.rugal.demo.swagger.model.NewStudentDto;
import ga.rugal.demo.swagger.model.StudentDto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * The Data Mapper For Student.
 *
 * @author Rugal Bernstein
 */
@Mapper(config = CentralConfig.class)
public interface StudentMapper {

  StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

  Student to(NewStudentDto student);

  Student to(StudentDto student);

  StudentDto from(Student student);

  NewStudentDto from2(Student student);
}
