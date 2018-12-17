package ga.rugal.upgrade.core.service.impl;

import ga.rugal.upgrade.core.dao.CourseDao;
import ga.rugal.upgrade.core.service.CourseService;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

  @Autowired
  @Setter
  private CourseDao dao;

  @Override
  public CourseDao getDao() {
    return this.dao;
  }
}
