package config;

import javax.sql.DataSource;

import ga.rugal.upgrade.core.entity.PackageInfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Core application context.
 *
 * @author Rugal Bernstein
 */
@ComponentScan(basePackageClasses = ga.rugal.PackageInfo.class)
@Configuration
@EnableJpaRepositories(basePackageClasses = ga.rugal.upgrade.core.dao.PackageInfo.class)
@EnableTransactionManagement
@EntityScan(basePackageClasses = PackageInfo.class)
@PropertySource("classpath:jdbc/${spring.profiles.active}.properties")
public class ApplicationContext {

  public static final String AUTOCOMMIT = "hibernate.connection.autocommit";

  public static final String FORMAT_SQL = "hibernate.format_sql";

  public static final String AUTO_DDL = "hibernate.hbm2ddl.auto";

  public static final String SHOW_SQL = "hibernate.show_sql";

  public static final String CONTEXT_CLASS = "hibernate.current_session_context_class";

  public static final String DIALECT = "hibernate.dialect";

  @Value("${jdbc.username}")
  private String username;

  @Value("${jdbc.password}")
  private String password;

  @Value("${jdbc.driverClassName}")
  private String driverClassName;

  @Value("${jdbc.url}")
  private String url;

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  //<editor-fold defaultstate="collapsed" desc="HikariCP Data Source Configuration">
  /**
   * Create data source.
   *
   * @return
   */
  @Bean(destroyMethod = "close")
  public DataSource dataSource() {
    final HikariDataSource dataSource = new HikariDataSource();
    dataSource.setUsername(this.username);
    dataSource.setPassword(this.password);
    dataSource.setJdbcUrl(this.url);
    dataSource.setDriverClassName(this.driverClassName);
    dataSource.setConnectionTestQuery("SELECT 1;");
    dataSource.setMaximumPoolSize(3);
    dataSource.setAutoCommit(false);
    return dataSource;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Hibernate Session factory configuration">
  /**
   * Create session factory.
   *
   * @param dataSource data source
   *
   * @return
   */
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource) {

    final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setDatabase(Database.POSTGRESQL);
    vendorAdapter.setGenerateDdl(true);

    final LocalContainerEntityManagerFactoryBean f = new LocalContainerEntityManagerFactoryBean();
    f.setJpaVendorAdapter(vendorAdapter);
    f.setPackagesToScan(PackageInfo.class.getPackage().getName());
    f.setDataSource(dataSource);

    return f;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Hibernate transaction manager">
  /**
   * Create transaction manager.
   *
   * @param emf entity manager
   *
   * @return
   */
  @Bean
  public PlatformTransactionManager tm(final LocalContainerEntityManagerFactoryBean emf) {
    final JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(emf.getObject());
    return txManager;
  }
  //</editor-fold>
}
