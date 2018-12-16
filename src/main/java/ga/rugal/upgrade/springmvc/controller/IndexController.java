package ga.rugal.upgrade.springmvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Index controller.
 *
 * @author Rugal Bernstein
 */
@RestController
public class IndexController {

  @GetMapping("/")
  public ResponseEntity<String> index() {
    return new ResponseEntity<>("Running", HttpStatus.OK);
  }
}
