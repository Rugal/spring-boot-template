package ga.rugal.demo.springmvc.mapper;

import ga.rugal.demo.core.entity.Student;
import ga.rugal.demo.openapi.model.NewStudentDto;
import ga.rugal.demo.openapi.model.StudentDto;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * The Data Mapper For Student.
 *
 * @author Rugal Bernstein
 */
@Mapper(config = CentralConfig.class)
@SuppressFBWarnings("UUF_UNUSED_PUBLIC_OR_PROTECTED_FIELD")
public interface StudentMapper {

  StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

  Student to(NewStudentDto student);

  Student to(StudentDto student);

  StudentDto from(Student student);

  NewStudentDto from2(Student student);
}
