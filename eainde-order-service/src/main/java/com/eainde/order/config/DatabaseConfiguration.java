package com.eainde.order.config;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManagerFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.eainde.order.repository",
    entityManagerFactoryRef = "entityManager",
    transactionManagerRef = "transactionManager")
public class DatabaseConfiguration {
  @Primary
  @Bean
  @ConfigurationProperties("spring.datasource")
  public DataSourceProperties dataSourceProperties() {
    return new DataSourceProperties();
  }

  @Primary
  @Bean
  @ConfigurationProperties("spring.datasource.configuration")
  public HikariDataSource dataSource() {
    return dataSourceProperties()
        .initializeDataSourceBuilder()
        .type(HikariDataSource.class)
        .build();
  }

  @Primary
  @Bean(name = "entityManager")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      EntityManagerFactoryBuilder builder) {
    return builder
        .dataSource(dataSource())
        .properties(hibernateProperties())
        .packages("com.eainde.order.entity")
        .persistenceUnit("pu")
        .build();
  }

  @Primary
  @Bean(name = "transactionManager")
  public PlatformTransactionManager transactionManager(
      @Qualifier("entityManager") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }

  private Map hibernateProperties() {
    Resource resource = new ClassPathResource("hibernate.properties");
    try {
      Properties properties = PropertiesLoaderUtils.loadProperties(resource);
      return properties.entrySet().stream()
          .collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue()));
    } catch (IOException e) {
      return new HashMap();
    }
  }
}
