package ga.rugal.demo.core.service.impl;

import ga.rugal.demo.core.service.impl.RegistrationServiceImpl;
import ga.rugal.UnitTestBase;
import ga.rugal.demo.core.dao.RegistrationDao;
import ga.rugal.demo.core.entity.Registration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 *
 * @author Rugal Bernstein
 */
public class RegistrationServiceImplTest extends UnitTestBase {

  @Autowired
  private Registration registration;

  private final RegistrationServiceImpl registrationService = new RegistrationServiceImpl();

  /**
   * @MockBean will automatically be injected into service.
   */
  @MockBean
  private RegistrationDao registrationDao;

  @Before
  public void before() {
    //We do it here for covering setter
    this.registrationService.setDao(this.registrationDao);
  }

  @Test
  public void test() {
    Assert.assertNotNull(this.registration);
  }
}
