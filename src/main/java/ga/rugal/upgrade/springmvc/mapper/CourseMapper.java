package ga.rugal.upgrade.springmvc.mapper;

import ga.rugal.upgrade.core.entity.Course;
import ga.rugal.upgrade.swagger.request.CourseDto;
import ga.rugal.upgrade.swagger.request.NewCourseDto;

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
}
