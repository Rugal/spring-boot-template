package ga.rugal.demo.core.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Test client.
 *
 * @author rugal
 */
@FeignClient(name = "test", url = "localhost:8000")
public interface TestClient {

  @GetMapping("/")
  void getTest();
}
