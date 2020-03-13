package config;

import java.util.ArrayList;
import java.util.List;

import ga.rugal.demo.springmvc.controller.PackageInfo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration for swagger 2.
 *
 * @author Rugal Bernstein
 */
@Configuration
@EnableSwagger2
@Profile("swagger")
public class SwaggerConfiguration {

  /**
   * Bearer authentication API key bean.
   *
   * @return API key
   */
  @Bean
  public ApiKey apiKey() {
    // The "bearerAuth" has to match what that is in contract.yml
    return new ApiKey("bearerAuth", "Authorization", "header");
  }

  /**
   * Basic API info.
   *
   * @return ApiInfo object
   */
  @Bean
  public ApiInfo apiInfo() {
    return new ApiInfo("Spring Boot Template Sample API",
                       "This is a simple API",
                       "1.0.0",
                       "",
                       new Contact("Rugal Bernstein", "https://rug.al", "this@rug.al"),
                       "Apache 2.0",
                       "http://www.apache.org/licenses/LICENSE-2.0.html",
                       new ArrayList<>());
  }

  /**
   * Docket bean.
   *
   * @param apiInfo API info object
   * @param apiKey  API key object
   *
   * @return bean object
   */
  @Bean
  public Docket apiDocket(final ApiInfo apiInfo, final ApiKey apiKey) {
    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo)
            .select()
            .apis(RequestHandlerSelectors.basePackage(PackageInfo.class.getPackage().getName()))
            .paths(PathSelectors.any())
            .build()
            // Add this to show "Authorize" button in Springfox UI
            .securitySchemes(List.of(apiKey));
  }
}
