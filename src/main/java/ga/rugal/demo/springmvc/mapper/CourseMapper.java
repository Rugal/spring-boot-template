package ga.rugal.demo.springmvc.mapper;

import ga.rugal.demo.core.entity.Course;
import ga.rugal.demo.openapi.model.CourseDto;
import ga.rugal.demo.openapi.model.NewCourseDto;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * The Data Mapper For Course.
 *
 * @author Rugal Bernstein
 */
@Mapper(config = CentralConfig.class)
@SuppressFBWarnings("UUF_UNUSED_PUBLIC_OR_PROTECTED_FIELD")
public interface CourseMapper {

  CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

  Course to(NewCourseDto course);

  Course to(CourseDto course);

  CourseDto from(Course course);

  NewCourseDto from2(Course course);
}
