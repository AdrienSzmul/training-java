package com.excilys.formation.computerdatabase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:/properties/dao.properties")
@Import(PersistenceJPAConfig.class)
@ComponentScan(basePackages = {
        "com.excilys.formation.computerdatabase.paginator",
        "com.excilys.formation.computerdatabase.ui",
        "com.excilys.formation.computerdatabase.controllers" })
public class MyWebAppConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
