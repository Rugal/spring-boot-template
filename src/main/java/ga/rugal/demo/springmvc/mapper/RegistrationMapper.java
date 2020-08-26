package ga.rugal.demo.springmvc.mapper;

import ga.rugal.demo.core.entity.Registration;
import ga.rugal.demo.graphql.RegistrationDto;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * The Data Mapper For Registration.
 *
 * @author Rugal Bernstein
 */
@Mapper(config = CentralConfig.class, uses = {CourseMapper.class, StudentMapper.class})
@SuppressFBWarnings("UUF_UNUSED_PUBLIC_OR_PROTECTED_FIELD")
public interface RegistrationMapper {

  RegistrationMapper I = Mappers.getMapper(RegistrationMapper.class);

  @Mappings({
    @Mapping(source = "registration.course", target = "course"),
    @Mapping(source = "registration.student", target = "student")})
  RegistrationDto from(Registration registration);
}
