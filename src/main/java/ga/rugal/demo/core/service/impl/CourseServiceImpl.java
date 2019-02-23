package ga.rugal.demo.core.service.impl;

import ga.rugal.demo.core.dao.CourseDao;
import ga.rugal.demo.core.service.CourseService;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

  @Autowired
  @Getter
  @Setter
  private CourseDao dao;
}
