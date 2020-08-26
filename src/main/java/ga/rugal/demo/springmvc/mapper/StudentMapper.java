package ga.rugal.demo.springmvc.mapper;

import ga.rugal.demo.core.entity.Student;
import ga.rugal.demo.graphql.StudentDto;
import ga.rugal.demo.graphql.StudentInputDto;

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

  StudentMapper I = Mappers.getMapper(StudentMapper.class);

  Student to(StudentInputDto student);

  StudentDto from(Student student);
}
