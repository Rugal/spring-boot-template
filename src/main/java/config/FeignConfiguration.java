package config;

import feign.Contract;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Application context for open feign.
 *
 * @author rugal
 */
@Configuration
@EnableFeignClients(basePackageClasses = ga.rugal.demo.openapi.client.api.CourseApi.class)
public class FeignConfiguration {

  @Bean
  public Contract feignContract() {
    return new Contract.Default();
  }
}
