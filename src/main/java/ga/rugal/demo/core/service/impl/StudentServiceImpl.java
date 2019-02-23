package ga.rugal.demo.core.service.impl;

import ga.rugal.demo.core.dao.StudentDao;
import ga.rugal.demo.core.service.StudentService;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  @Getter
  @Setter
  private StudentDao dao;
}
