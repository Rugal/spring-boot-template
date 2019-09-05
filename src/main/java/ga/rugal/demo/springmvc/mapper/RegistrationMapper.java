package ga.rugal.demo.springmvc.mapper;

import ga.rugal.demo.core.entity.Registration;
import ga.rugal.demo.openapi.model.NewRegistrationDto;
import ga.rugal.demo.openapi.model.RegistrationDto;

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
@Mapper(config = CentralConfig.class)
@SuppressFBWarnings("UUF_UNUSED_PUBLIC_OR_PROTECTED_FIELD")
public interface RegistrationMapper {

  RegistrationMapper INSTANCE = Mappers.getMapper(RegistrationMapper.class);

  @Mappings({
    @Mapping(source = "registration.course.cid", target = "cid"),
    @Mapping(source = "registration.student.sid", target = "sid")})
  RegistrationDto from(Registration registration);

  @Mappings({
    @Mapping(source = "registration.course.cid", target = "cid"),
    @Mapping(source = "registration.student.sid", target = "sid")})
  NewRegistrationDto from2(Registration registration);
}
