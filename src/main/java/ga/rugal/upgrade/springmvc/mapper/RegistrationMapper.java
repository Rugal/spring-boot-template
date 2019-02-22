package ga.rugal.upgrade.springmvc.mapper;

import ga.rugal.upgrade.core.entity.Registration;
import ga.rugal.upgrade.swagger.model.NewRegistrationDto;
import ga.rugal.upgrade.swagger.model.RegistrationDto;

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
