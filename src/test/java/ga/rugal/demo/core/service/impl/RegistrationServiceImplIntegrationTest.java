package ga.rugal.demo.core.service.impl;

import ga.rugal.IntegrationTestBase;
import ga.rugal.demo.core.service.RegistrationService;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Rugal Bernstein
 */
public class RegistrationServiceImplIntegrationTest extends IntegrationTestBase {

  @Autowired
  private RegistrationService registrationService;

  @Test
  public void test() {
    Assert.assertNotNull(this.registrationService.getDao());
  }
}
