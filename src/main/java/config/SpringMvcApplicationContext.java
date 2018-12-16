package config;

import ga.rugal.upgrade.springmvc.controller.PackageInfo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Java based Web context configuration class. Including argument resolution, message converter,
 * view resolution etc.,
 *
 * @author Rugal Bernstein
 * @since 0.2
 */
@ComponentScan(basePackageClasses = PackageInfo.class)
@Configuration
@EnableWebMvc
public class SpringMvcApplicationContext implements WebMvcConfigurer {

  @Override
  public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  @Override
  public void addCorsMappings(final CorsRegistry registry) {
    registry.addMapping("/**")
      .allowedOrigins("*")
      .allowedMethods("*")
      .allowedHeaders("*");
  }
}
