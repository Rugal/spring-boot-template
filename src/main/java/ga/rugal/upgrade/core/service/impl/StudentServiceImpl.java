package ga.rugal.upgrade.core.service.impl;

import ga.rugal.upgrade.core.dao.StudentDao;
import ga.rugal.upgrade.core.service.StudentService;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  @Setter
  private StudentDao dao;

  @Override
  public StudentDao getDao() {
    return this.dao;
  }
}
