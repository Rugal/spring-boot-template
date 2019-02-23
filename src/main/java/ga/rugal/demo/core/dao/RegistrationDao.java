package ga.rugal.demo.core.dao;

import ga.rugal.demo.core.entity.Registration;

import org.springframework.data.repository.CrudRepository;

public interface RegistrationDao extends CrudRepository<Registration, Integer> {
}
