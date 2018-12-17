package ga.rugal.upgrade.core.service.impl;

import ga.rugal.IntegrationTestBase;
import ga.rugal.upgrade.core.service.RegistrationService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Rugal Bernstein
 */
public class RegistrationServiceImplIntegrationTest extends IntegrationTestBase {

  @Autowired
  private RegistrationService registrationService;

  @Before
  public void before() {
  }

  @Test
  public void test() {
    Assert.assertNotNull(this.registrationService.getDao());
  }
}
