package ga.rugal.demo.core.service.impl;

import ga.rugal.demo.core.dao.RegistrationDao;
import ga.rugal.demo.core.service.RegistrationService;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

  @Autowired
  @Getter
  @Setter
  private RegistrationDao dao;
}
