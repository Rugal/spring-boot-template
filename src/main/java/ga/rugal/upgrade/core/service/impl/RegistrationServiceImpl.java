package ga.rugal.upgrade.core.service.impl;

import ga.rugal.upgrade.core.dao.RegistrationDao;
import ga.rugal.upgrade.core.service.RegistrationService;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

  @Autowired
  @Setter
  private RegistrationDao dao;

  @Override
  public RegistrationDao getDao() {
    return this.dao;
  }
}
