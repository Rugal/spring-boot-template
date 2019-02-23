package ga.rugal.demo.core.dao;

import ga.rugal.demo.core.entity.Student;

import org.springframework.data.repository.CrudRepository;

public interface StudentDao extends CrudRepository<Student, Integer> {

}
