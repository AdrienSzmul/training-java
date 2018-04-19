package com.excilys.formation.computerdatabase.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("classpath:/properties/dao.properties")
@ComponentScan(basePackages = { "com.excilys.formation.computerdatabase.config",
        "com.excilys.formation.computerdatabase.persistence.dao",
        "com.excilys.formation.computerdatabase.service",
        "com.excilys.formation.computerdatabase.paginator",
        "com.excilys.formation.computerdatabase.mapper" })
public class MyWebAppConfig implements WebMvcConfigurer {
    private static final String URL = "url";
    private static final String PASSWORD = "password";
    private static final String DRIVER = "driver";
    private static final String USER = "utilisateur";
    @Autowired
    private Environment env;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty(DRIVER));
        dataSource.setUrl(env.getRequiredProperty(URL));
        dataSource.setUsername(env.getRequiredProperty(USER));
        dataSource.setPassword(env.getRequiredProperty(PASSWORD));
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
