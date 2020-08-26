package ga.rugal.demo.springmvc.mapper;

import ga.rugal.demo.core.entity.Course;
import ga.rugal.demo.graphql.CourseDto;
import ga.rugal.demo.graphql.CourseInputDto;

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

  CourseMapper I = Mappers.getMapper(CourseMapper.class);

  Course to(CourseInputDto course);

  Course to(CourseDto course);

  CourseDto from(Course course);
}
