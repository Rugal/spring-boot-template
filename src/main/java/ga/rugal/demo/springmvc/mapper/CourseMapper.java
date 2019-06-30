package ga.rugal.demo.springmvc.mapper;

import ga.rugal.demo.core.entity.Course;
import ga.rugal.demo.openapi.model.CourseDto;
import ga.rugal.demo.openapi.model.NewCourseDto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * The Data Mapper For Course.
 *
 * @author Rugal Bernstein
 */
@Mapper(config = CentralConfig.class)
public interface CourseMapper {

  CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

  Course to(NewCourseDto course);

  Course to(CourseDto course);

  CourseDto from(Course course);

  NewCourseDto from2(Course course);
}
