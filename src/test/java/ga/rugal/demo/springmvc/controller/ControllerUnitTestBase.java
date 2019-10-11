package ga.rugal.demo.springmvc.controller;

import ga.rugal.UnitTestBase;

import org.junit.jupiter.api.Disabled;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author Rugal Bernstein
 */
@ComponentScan(basePackageClasses = ga.rugal.demo.springmvc.controller.PackageInfo.class)
@Disabled
public class ControllerUnitTestBase extends UnitTestBase {

}
