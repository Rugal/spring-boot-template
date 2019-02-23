package ga.rugal.demo.core.dao;

import ga.rugal.demo.core.entity.Course;

import org.springframework.data.repository.CrudRepository;

public interface CourseDao extends CrudRepository<Course, Integer> {

  //findOne, delete, save, count, etc., are inherited from CruiRepository
}
