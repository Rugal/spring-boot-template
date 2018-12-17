package ga.rugal.upgrade.core.dao;

import ga.rugal.upgrade.core.entity.Student;

import org.springframework.data.repository.CrudRepository;

public interface StudentDao extends CrudRepository<Student, Integer> {

}
