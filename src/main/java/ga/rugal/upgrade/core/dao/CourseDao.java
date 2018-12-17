package ga.rugal.upgrade.core.dao;

import ga.rugal.upgrade.core.entity.Course;

import org.springframework.data.repository.CrudRepository;

public interface CourseDao extends CrudRepository<Course, Integer> {

  //findOne, delete, save, count, etc., are inherited from CruiRepository
}
