package ga.rugal.demo.springmvc.controller;

import ga.rugal.demo.core.client.TestClient;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Course controller.
 *
 * @author Rugal Bernstein
 */
@RestController
public class IndexController {

  @Autowired
  @Setter
  private TestClient client;

  @GetMapping("/test")
  public ResponseEntity<Void> test() {
    this.client.getTest();
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
