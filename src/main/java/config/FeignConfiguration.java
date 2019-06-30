package config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * Application context for open feign.
 *
 * @author rugal
 */
@Configuration
@EnableFeignClients(basePackageClasses = ga.rugal.demo.core.client.PackageInfo.class)
public class FeignConfiguration {

}
