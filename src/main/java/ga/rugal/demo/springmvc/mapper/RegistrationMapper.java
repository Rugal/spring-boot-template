package ga.rugal.demo.springmvc.mapper;

import ga.rugal.demo.core.entity.Registration;
import ga.rugal.demo.swagger.model.NewRegistrationDto;
import ga.rugal.demo.swagger.model.RegistrationDto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * The Data Mapper For Registration.
 *
 * @author Rugal Bernstein
 */
@Mapper(config = CentralConfig.class)
public interface RegistrationMapper {

  RegistrationMapper INSTANCE = Mappers.getMapper(RegistrationMapper.class);

  Registration to(NewRegistrationDto course);

  Registration to(RegistrationDto course);

  RegistrationDto from(Registration course);
}
