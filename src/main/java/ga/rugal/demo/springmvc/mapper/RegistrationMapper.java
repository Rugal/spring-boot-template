package ga.rugal.demo.springmvc.mapper;

import ga.rugal.demo.core.entity.Registration;
import ga.rugal.demo.openapi.model.NewRegistrationDto;
import ga.rugal.demo.openapi.model.RegistrationDto;

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
