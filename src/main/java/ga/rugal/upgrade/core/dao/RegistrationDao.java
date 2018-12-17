package ga.rugal.upgrade.core.dao;

import ga.rugal.upgrade.core.entity.Registration;

import org.springframework.data.repository.CrudRepository;

public interface RegistrationDao extends CrudRepository<Registration, Integer> {
}
