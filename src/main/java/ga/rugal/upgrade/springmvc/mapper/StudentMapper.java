package ga.rugal.upgrade.springmvc.mapper;

import ga.rugal.upgrade.core.entity.Student;
import ga.rugal.upgrade.swagger.request.NewStudentDto;
import ga.rugal.upgrade.swagger.request.StudentDto;

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

  Student to(NewStudentDto course);

  Student to(StudentDto course);

  StudentDto from(Student course);
}
