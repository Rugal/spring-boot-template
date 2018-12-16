package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Core application context.
 *
 * @author Rugal Bernstein
 */
@Configuration
public class ApplicationContext {

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}
